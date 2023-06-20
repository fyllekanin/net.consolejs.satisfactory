package net.consolejs.satisfactory.restservice.satisfactoryimport;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.restservice.satisfactoryimport.provider.SatisfactoryImportProvider;
import net.consolejs.satisfactory.service.file.FileService;
import net.consolejs.satisfactory.service.http.HttpService;
import org.glassfish.grizzly.utils.Pair;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Path("/api/v1")
public class RestImportService {
    private static final String GAME_VERSION_KEY = "gameVersion";
    private static final String DATA_KEY = "data";
    private static final String DOCS_NAME = "Docs.json";
    private static final String RESOURCE_ENDING = ".png";
    private static final Logger LOGGER = Logger.getLogger(RestImportService.class.getName());
    @Inject
    private HttpService myHttpService;
    @Inject
    private FileService myFileService;

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response satisfactoryImport(FormDataMultiPart formParams) {
        try {
            SatisfactoryImportProvider provider = new SatisfactoryImportProvider(formParams);
            SatisfactoryImport satisfactoryImport = provider.getSatisfactoryImport();

            for (Pair<ZipEntry, byte[]> entryPair : satisfactoryImport.getImageResources()) {
                myFileService.writeFile(String.format("%s/%s", provider.getGameVersion(), entryPair.getFirst().getName()),
                        entryPair.getSecond());
            }

        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, String.format("Import failed: \"%s\"", exception.getMessage()));
            return Response.status(500)
                    .entity("Import failed")
                    .build();
        }

        return Response.status(200).build();
    }

    private SatisfactoryImport getSatisfactoryImport(InputStream inputStream) {
        SatisfactoryImport.Builder builder = SatisfactoryImport.newBuilder();
        ZipInputStream stream = new ZipInputStream(inputStream);
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();

        ZipEntry entry;
        try {
            while ((entry = stream.getNextEntry()) != null) {
                if (entry.getName().equals(DOCS_NAME)) {
                    builder.withDocs(new Pair<>(entry, stream.readAllBytes()));
                } else if (entry.getName().endsWith(RESOURCE_ENDING)) {
                    imageResources.add(new Pair<>(entry, stream.readAllBytes()));
                }
            }
            stream.close();
        } catch (IOException exception) {
            // Empty
        }
        return builder
                .withImageResources(imageResources)
                .build();
    }

    private List<SatisfactoryClassWrapper> getDocs(ZipInputStream zipStream) {
        return null;
    }
}

