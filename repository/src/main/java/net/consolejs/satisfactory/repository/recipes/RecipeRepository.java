package net.consolejs.satisfactory.repository.recipes;

import com.google.gson.Gson;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import org.bson.Document;

import java.util.logging.Logger;

public class RecipeRepository {
    private static final Logger LOGGER = Logger.getLogger(RecipeRepository.class.getName());
    private static final String COLLECTION = "recipes";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public RecipeRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
        myCollection.createIndex(Indexes.ascending("gameVersion"));
        myCollection.createIndex(Indexes.descending("gameVersion", "className"),
                new IndexOptions().unique(true));
    }

    public void create(RecipeDocument document) {
        try {
            myCollection.insertOne(getDocument(document));
        } catch (MongoWriteException exception) {
            LOGGER.severe(String.format("Failed to insert recipe, gameVersion: \"%s\", className: \"%s\"",
                    document.getGameVersion(), document.getClassName()));
            LOGGER.fine(exception.getMessage());
        }
    }

    public RecipeDocument getRecipeDocument(Document document) {
        return GSON.fromJson(GSON.toJson(document), RecipeDocument.class);
    }

    public Document getDocument(RecipeDocument recipeDocument) {
        return Document.parse(GSON.toJson(recipeDocument));
    }
}
