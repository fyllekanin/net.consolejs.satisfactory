package net.consolejs.satisfactory.repository.manufacturer;

import com.google.gson.Gson;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import net.consolejs.satisfactory.entityview.document.manufacturer.ManufacturerDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;

public class ManufacturerRepository {
    private static final String GAME_VERSION = "gameVersion";
    private static final String CLASS_NAME = "className";
    private static final Logger LOGGER = Logger.getLogger(ManufacturerRepository.class.getName());
    private static final String COLLECTION = "manufacturers";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public ManufacturerRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
        myCollection.createIndex(Indexes.ascending(GAME_VERSION));
        myCollection.createIndex(Indexes.descending(GAME_VERSION, CLASS_NAME),
                new IndexOptions().unique(true));
    }

    public void create(ManufacturerDocument document) {
        try {
            myCollection.insertOne(getDocument(document));
        } catch (MongoWriteException exception) {
            LOGGER.severe(String.format("Failed to insert manufacturer, gameVersion: \"%s\", className: \"%s\"",
                    document.getGameVersion(), document.getClassName()));
            LOGGER.fine(exception.getMessage());
        }
    }

    public void deleteByGameVersion(String gameVersion) {
        try {
            myCollection.deleteMany(new Document(GAME_VERSION, gameVersion));
        } catch (Exception exception) {
            LOGGER.severe(String.format("Failed to delete manufacturers for gameVersion: \"%s\"",
                    gameVersion));
        }
    }

    public ManufacturerDocument findByClassName(String gameVersion, String className) {
        Bson query = Filters.and(Filters.exists(GAME_VERSION), Filters.eq(GAME_VERSION, gameVersion),
                Filters.exists(CLASS_NAME), Filters.eq(CLASS_NAME, className));
        Document document = myCollection.find(query).first();
        return document == null ? null : getManufacturerDocument(document);
    }

    private ManufacturerDocument getManufacturerDocument(Document document) {
        return GSON.fromJson(GSON.toJson(document), ManufacturerDocument.class);
    }

    private Document getDocument(ManufacturerDocument manufacturerDocument) {
        return Document.parse(GSON.toJson(manufacturerDocument));
    }
}
