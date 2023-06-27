package net.consolejs.satisfactory.repository.gameimport;

import com.google.gson.Gson;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import net.consolejs.satisfactory.entityview.document.gameimport.GameImportDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;

public class GameImportRepository {
    private static final String GAME_VERSION = "gameVersion";
    private static final String TYPE = "type";
    private static final Logger LOGGER = Logger.getLogger(GameImportRepository.class.getName());
    private static final String COLLECTION = "gameImports";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public GameImportRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
        myCollection.createIndex(Indexes.ascending(GAME_VERSION, TYPE));
        myCollection.createIndex(Indexes.descending(GAME_VERSION),
                                 new IndexOptions().unique(true));
    }

    public boolean isImportAlreadyPresent(String gameVersion) {
        Bson query = Filters.and(Filters.exists(GAME_VERSION), Filters.eq(GAME_VERSION, gameVersion));

        return myCollection.countDocuments(query) > 0;
    }

    public void create(GameImportDocument document) {
        try {
            myCollection.insertOne(getDocument(document));
        } catch (MongoWriteException exception) {
            LOGGER.severe(String.format("Failed to insert game import, gameVersion: \"%s\"",
                                        document.getGameVersion()));
            LOGGER.fine(exception.getMessage());
        }
    }

    public void deleteByGameVersion(String gameVersion) {
        try {
            myCollection.deleteMany(new Document(GAME_VERSION, gameVersion));
        } catch (Exception exception) {
            LOGGER.severe(String.format("Failed to delete import for gameVersion: \"%s\"",
                                        gameVersion));
        }
    }

    private Document getDocument(GameImportDocument gameImportDocument) {
        return Document.parse(GSON.toJson(gameImportDocument));
    }
}
