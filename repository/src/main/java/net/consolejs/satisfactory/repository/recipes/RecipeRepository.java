package net.consolejs.satisfactory.repository.recipes;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import org.bson.Document;

public class RecipeRepository {
    private static final String COLLECTION = "recipes";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public RecipeRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
    }

    public void create(RecipeDocument document) {
        myCollection.insertOne(getDocument(document));
    }

    public RecipeDocument getRecipeDocument(Document document) {
        return GSON.fromJson(GSON.toJson(document), RecipeDocument.class);
    }

    public Document getDocument(RecipeDocument recipeDocument) {
        return Document.parse(GSON.toJson(recipeDocument));
    }
}
