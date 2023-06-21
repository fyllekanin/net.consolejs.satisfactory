package net.consolejs.satisfactory.repository;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.consolejs.satisfactory.repository.recipes.RecipeRepository;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jvnet.hk2.annotations.Service;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Service()
public class RepositoryFactory {
    private static final String PORT_KEY = "SATISFACTORY_MONGODB_PORT";
    private static final String HOST_KEY = "SATISFACTORY_MONGODB_HOST";
    private static final String USERNAME_KEY = "SATISFACTORY_MONGODB_USERNAME";
    private static final String PASSWORD_KEY = "SATISFACTORY_MONGODB_PASSWORD";
    private static final String AUTH_DATABASE_KEY = "SATISFACTORY_MONGODB_AUTH_DATABASE";
    private static final String DATABASE_KEY = "SATISFACTORY_MONGODB_DATABASE";

    private final MongoClient myClient;
    private final MongoDatabase myDatabase;

    public RepositoryFactory() {
        String uri = String.format("mongodb://%s:%s@%s:%s/%s?retryWrites=true&w=majority",
                System.getProperty(USERNAME_KEY),
                System.getProperty(PASSWORD_KEY),
                System.getProperty(HOST_KEY),
                System.getProperty(PORT_KEY),
                System.getProperty(AUTH_DATABASE_KEY));

        myClient = MongoClients.create(uri);
        myDatabase = myClient.getDatabase(System.getProperty(DATABASE_KEY));

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder()
                        .automatic(true).build()));
        myDatabase.withCodecRegistry(pojoCodecRegistry);
    }

    public <T> T of(Class<T> clazz) {
        if (clazz.equals(RecipeRepository.class)) {
            return (T) new RecipeRepository(myDatabase);
        }
        return null;
    }
}
