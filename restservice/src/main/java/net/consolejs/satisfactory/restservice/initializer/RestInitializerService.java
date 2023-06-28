package net.consolejs.satisfactory.restservice.initializer;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.restservice.initializer.provider.InitialDataProvider;

import java.util.logging.Logger;

@Path("/api/v1/initial")
public class RestInitializerService {
    private static final Logger LOGGER = Logger.getLogger(RestInitializerService.class.getName());
    @Inject
    private RepositoryFactory myRepositoryFactory;

    private InitialDataProvider myProvider;

    @PostConstruct
    public void init() {
        myProvider = new InitialDataProvider(myRepositoryFactory);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitial() {
        return Response.status(Response.Status.OK)
                       .entity(new Gson().toJson(myProvider.getInitialData()))
                       .build();
    }
}
