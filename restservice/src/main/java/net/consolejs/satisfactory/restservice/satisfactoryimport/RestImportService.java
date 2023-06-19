package net.consolejs.restservice.satisfactoryimport;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.consolejs.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.service.http.HttpService;

import java.util.logging.Logger;

@Path("/api/v1")
public class RestImportService {
    private static final Logger LOGGER = Logger.getLogger(RestImportService.class.getName());
    @Inject
    private HttpService myHttpService;

    @POST
    @Path("/import")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayHello(String json) {
        SatisfactoryImport satisfactoryImport = new Gson().fromJson(json, SatisfactoryImport.class);

        LOGGER.info("Classes: " + satisfactoryImport.getDocs().size());
        return Response.status(200).build();
    }
}

