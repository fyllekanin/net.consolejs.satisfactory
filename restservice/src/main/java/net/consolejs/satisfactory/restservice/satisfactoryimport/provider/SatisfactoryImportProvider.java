package net.consolejs.satisfactory.restservice.satisfactoryimport.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.consolejs.satisfactory.entityview.document.gameimport.GameImportType;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import org.glassfish.grizzly.utils.Pair;
import org.glassfish.jersey.internal.guava.Preconditions;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SatisfactoryImportProvider {
    private static final String GAME_VERSION_KEY = "gameVersion";
    private static final String TYPE_KEY = "type";
    private static final String DATA_KEY = "data";
    private static final String DOCS_NAME = "Docs.json";
    private static final String RESOURCE_ENDING = ".png";

    private final FormDataMultiPart myFormData;

    public SatisfactoryImportProvider(FormDataMultiPart formData) {
        myFormData = Preconditions.checkNotNull(formData, "Form data can not be null");
    }

    public String getGameVersion() {
        return myFormData.getField(GAME_VERSION_KEY)
                         .getValue();
    }

    public GameImportType getType() {
        return GameImportType.valueOf(myFormData.getField(TYPE_KEY)
                                                .getValue());
    }

    public SatisfactoryImport getSatisfactoryImport() throws IOException {
        InputStream dataStream = myFormData
                .getField(DATA_KEY)
                .getEntityAs(InputStream.class);
        SatisfactoryImport.Builder builder = SatisfactoryImport.newBuilder();
        ZipInputStream stream = new ZipInputStream(dataStream);
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();

        ZipEntry entry;

        while ((entry = stream.getNextEntry()) != null) {
            if (entry
                    .getName()
                    .equals(DOCS_NAME)) {
                builder.withDocs(new Pair<>(entry, stream.readAllBytes()));
            } else if (entry
                    .getName()
                    .endsWith(RESOURCE_ENDING)) {
                imageResources.add(new Pair<>(entry, stream.readAllBytes()));
            }
        }
        stream.close();
        dataStream.close();

        return builder
                .withImageResources(imageResources)
                .build();
    }

    public List<SatisfactoryClassWrapper> getSatisfactoryClassWrappers(SatisfactoryImport satisfactoryImport) {
        String json = new String(satisfactoryImport
                                         .getDocs()
                                         .getSecond(), StandardCharsets.UTF_8);
        return new Gson().fromJson(json, new TypeToken<ArrayList<SatisfactoryClassWrapper>>() {
        }.getType());
    }
}
