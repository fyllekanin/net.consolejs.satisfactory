package net.consolejs.satisfactory.repository.extractor;

import com.google.gson.Gson;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import net.consolejs.satisfactory.entityview.document.extractor.ExtractorDocument;
import net.consolejs.satisfactory.repository.manufacturer.ManufacturerRepository;
import org.bson.Document;

import java.util.logging.Logger;

public class ExtractorRepository {
    private static final String GAME_VERSION = "gameVersion";
    private static final String CLASS_NAME = "className";
    private static final Logger LOGGER = Logger.getLogger(ManufacturerRepository.class.getName());
    private static final String COLLECTION = "extractors";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public ExtractorRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
        myCollection.createIndex(Indexes.ascending(GAME_VERSION));
        myCollection.createIndex(Indexes.descending(GAME_VERSION, CLASS_NAME),
                new IndexOptions().unique(true));
    }

    public void create(ExtractorDocument document) {
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

    private ExtractorDocument getExtractorDocument(Document document) {
        return GSON.fromJson(GSON.toJson(document), ExtractorDocument.class);
    }

    private Document getDocument(ExtractorDocument extractorDocument) {
        return Document.parse(GSON.toJson(extractorDocument));
    }
}
