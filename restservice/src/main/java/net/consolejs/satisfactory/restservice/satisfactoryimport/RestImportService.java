package net.consolejs.satisfactory.restservice.satisfactoryimport;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.manufacturer.ManufacturerRepository;
import net.consolejs.satisfactory.repository.recipe.RecipeRepository;
import net.consolejs.satisfactory.repository.resource.ResourceRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.importer.SatisfactoryImporter;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/api/v1")
public class RestImportService {
    private static final Logger LOGGER = Logger.getLogger(RestImportService.class.getName());
    @Inject
    private FileService myFileService;
    @Inject
    private RepositoryFactory myRepositoryFactory;

    private SatisfactoryImporter mySatisfactoryImporter;

    @PostConstruct
    public void init() {
        mySatisfactoryImporter = new SatisfactoryImporter(myFileService, myRepositoryFactory);
    }

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response satisfactoryImportCreate(FormDataMultiPart formParams) {
        try {
            if (mySatisfactoryImporter.isAlreadyImported(formParams)) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("The provided gameVersion is already imported")
                        .build();
            }
            mySatisfactoryImporter.run(formParams);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, String.format("Import failed: \"%s\"", exception.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Import failed")
                    .build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/import/{gameVersion}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response satisfactoryImportDelete(@PathParam("gameVersion") String gameVersion) {
        try {
            if (!mySatisfactoryImporter.isAlreadyImported(gameVersion)) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The provided gameVersion is not imported")
                        .build();
            }

            myFileService.deleteDirectory(gameVersion);
            myRepositoryFactory.of(RecipeRepository.class).deleteByGameVersion(gameVersion);
            myRepositoryFactory.of(ResourceRepository.class).deleteByGameVersion(gameVersion);
            myRepositoryFactory.of(ManufacturerRepository.class).deleteByGameVersion(gameVersion);
        } catch (Exception exception) {
            LOGGER.log(Level.SEVERE, String.format("Import failed: \"%s\"", exception.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Import failed")
                    .build();
        }

        return Response.status(Response.Status.OK).build();
    }
}

