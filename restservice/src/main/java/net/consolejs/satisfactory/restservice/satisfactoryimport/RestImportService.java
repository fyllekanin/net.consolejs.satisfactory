package net.consolejs.satisfactory.restservice.satisfactoryimport;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.restservice.satisfactoryimport.importer.ImageResourceImporter;
import net.consolejs.satisfactory.restservice.satisfactoryimport.importer.RecipeImporter;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.restservice.satisfactoryimport.provider.SatisfactoryImportProvider;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/api/v1")
public class RestImportService {
    private static final Logger LOGGER = Logger.getLogger(RestImportService.class.getName());
    @Inject
    private FileService myFileService;
    @Inject
    private RepositoryFactory myRepositoryFactory;

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response satisfactoryImport(FormDataMultiPart formParams) {
        try {
            SatisfactoryImportProvider provider = new SatisfactoryImportProvider(formParams);
            SatisfactoryImport satisfactoryImport = provider.getSatisfactoryImport();

            new ImageResourceImporter(myFileService).importImageResources(satisfactoryImport, provider.getGameVersion());
            new RecipeImporter(myRepositoryFactory).importRecipes(provider.getSatisfactoryClassWrappers(satisfactoryImport),
                    provider.getGameVersion());

        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, String.format("Import failed: \"%s\"", exception.getMessage()));
            return Response.status(500)
                    .entity("Import failed")
                    .build();
        }

        return Response.status(200).build();
    }
}

