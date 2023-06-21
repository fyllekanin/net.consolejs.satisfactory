package net.consolejs.satisfactory.repository.recipe;

import com.google.gson.Gson;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;

public class RecipeRepository {
    private static final String GAME_VERSION = "gameVersion";
    private static final String CLASS_NAME = "className";
    private static final Logger LOGGER = Logger.getLogger(RecipeRepository.class.getName());
    private static final String COLLECTION = "recipes";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public RecipeRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
        myCollection.createIndex(Indexes.ascending(GAME_VERSION));
        myCollection.createIndex(Indexes.descending(GAME_VERSION, CLASS_NAME),
                new IndexOptions().unique(true));
    }

    public boolean isAnyRecipeForGameVersion(String gameVersion) {
        Bson query = Filters.and(Filters.exists(GAME_VERSION), Filters.eq(GAME_VERSION, gameVersion));

        return myCollection.countDocuments(query) > 0;
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

    public void deleteByGameVersion(String gameVersion) {
        try {
            myCollection.deleteMany(new Document(GAME_VERSION, gameVersion));
        } catch (Exception exception) {
            LOGGER.severe(String.format("Failed to delete recipes for gameVersion: \"%s\"",
                    gameVersion));
        }
    }

    public RecipeDocument getRecipeDocument(Document document) {
        return GSON.fromJson(GSON.toJson(document), RecipeDocument.class);
    }

    public Document getDocument(RecipeDocument recipeDocument) {
        return Document.parse(GSON.toJson(recipeDocument));
    }
}
