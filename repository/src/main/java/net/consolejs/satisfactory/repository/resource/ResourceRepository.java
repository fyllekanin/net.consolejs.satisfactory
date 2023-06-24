package net.consolejs.satisfactory.repository.resource;

import com.google.gson.Gson;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import net.consolejs.satisfactory.entityview.document.resource.ResourceDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;

public class ResourceRepository {
    private static final String GAME_VERSION = "gameVersion";
    private static final String CLASS_NAME = "className";
    private static final Logger LOGGER = Logger.getLogger(ResourceRepository.class.getName());
    private static final String COLLECTION = "resources";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public ResourceRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
        myCollection.createIndex(Indexes.ascending(GAME_VERSION));
        myCollection.createIndex(Indexes.descending(GAME_VERSION, CLASS_NAME),
                                 new IndexOptions().unique(true));
    }

    public void create(ResourceDocument document) {
        try {
            myCollection.insertOne(getDocument(document));
        } catch (MongoWriteException exception) {
            LOGGER.severe(String.format("Failed to insert resource, gameVersion: \"%s\", className: \"%s\"",
                                        document.getGameVersion(), document.getClassName()));
            LOGGER.fine(exception.getMessage());
        }
    }

    public ResourceDocument findByClassName(String gameVersion, String className) {
        Bson query = Filters.and(Filters.exists(GAME_VERSION), Filters.eq(GAME_VERSION, gameVersion),
                                 Filters.exists(CLASS_NAME), Filters.eq(CLASS_NAME, className));
        Document document = myCollection
                .find(query)
                .first();
        return document == null ? null : getResourceDocument(document);
    }

    public void deleteByGameVersion(String gameVersion) {
        try {
            myCollection.deleteMany(new Document(GAME_VERSION, gameVersion));
        } catch (Exception exception) {
            LOGGER.severe(String.format("Failed to delete resources for gameVersion: \"%s\"",
                                        gameVersion));
        }
    }

    private ResourceDocument getResourceDocument(Document document) {
        return GSON.fromJson(GSON.toJson(document), ResourceDocument.class);
    }

    private Document getDocument(ResourceDocument resourceDocument) {
        return Document.parse(GSON.toJson(resourceDocument));
    }
}
