package net.consolejs.satisfactory.repository.itemdescriptor;

import com.google.gson.Gson;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemDescriptorDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.logging.Logger;

public class ItemDescriptorRepository {
    private static final String GAME_VERSION = "gameVersion";
    private static final String CLASS_NAME = "className";
    private static final Logger LOGGER = Logger.getLogger(ItemDescriptorRepository.class.getName());
    private static final String COLLECTION = "items";
    private static final Gson GSON = new Gson();

    private final MongoCollection<Document> myCollection;

    public ItemDescriptorRepository(MongoDatabase database) {
        myCollection = database.getCollection(COLLECTION);
        myCollection.createIndex(Indexes.ascending(GAME_VERSION));
        myCollection.createIndex(Indexes.descending(GAME_VERSION, CLASS_NAME),
                new IndexOptions().unique(true));
    }

    public boolean isAnyRecipeForGameVersion(String gameVersion) {
        Bson query = Filters.and(Filters.exists(GAME_VERSION), Filters.eq(GAME_VERSION, gameVersion));

        return myCollection.countDocuments(query) > 0;
    }

    public void create(ItemDescriptorDocument document) {
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
            LOGGER.severe(String.format("Failed to delete itemDescriptor for gameVersion: \"%s\"",
                    gameVersion));
        }
    }

    public ItemDescriptorDocument findByClassName(String gameVersion, String className) {
        Bson query = Filters.and(Filters.exists(GAME_VERSION), Filters.eq(GAME_VERSION, gameVersion),
                Filters.exists(CLASS_NAME), Filters.eq(CLASS_NAME, className));
        Document document = myCollection.find(query).first();
        return document == null ? null : getItemDescriptorDocument(document);
    }

    private ItemDescriptorDocument getItemDescriptorDocument(Document document) {
        return GSON.fromJson(GSON.toJson(document), ItemDescriptorDocument.class);
    }

    private Document getDocument(ItemDescriptorDocument itemDescriptorDocument) {
        return Document.parse(GSON.toJson(itemDescriptorDocument));
    }
}