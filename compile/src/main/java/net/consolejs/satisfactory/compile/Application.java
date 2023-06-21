package net.consolejs.satisfactory.compile;

import io.github.cdimascio.dotenv.Dotenv;
import net.consolejs.satisfactory.repository.RepositoryResolver;
import net.consolejs.satisfactory.service.ServiceResolver;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Application {

    // Base URI the Grizzly HTTP server will listen on
    private static final String BASE_URI = "http://0.0.0.0:8000";

    public static void main(String[] args) throws IOException {
        // Create a resource config that scans for JAX-RS resources and providers in com.javahelps package
        Dotenv.configure()
                .systemProperties()
                .load();

        ResourceConfig config = new ResourceConfig()
                .packages(true, "net.consolejs.satisfactory.restservice")
                .register(new ServiceResolver())
                .register(new RepositoryResolver());
        // Create and start a grizzly http server
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
        System.out.printf("Jersey app started with endpoints available at %s%nHit Ctrl-C to stop it...%n", BASE_URI);

        // Wait for user input before shutting down the server
        System.in.read();

        // Shutdown the server
        httpServer.shutdown();
    }
}