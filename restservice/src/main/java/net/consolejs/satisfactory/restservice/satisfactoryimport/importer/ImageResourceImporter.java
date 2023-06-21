package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.grizzly.utils.Pair;

import java.util.zip.ZipEntry;

public class ImageResourceImporter implements Runnable {
    private final FileService myFileService;
    private final SatisfactoryImport mySatisfactoryImport;
    private final String myGameVersion;

    public ImageResourceImporter(FileService fileService, SatisfactoryImport satisfactoryImport, String gameVersion) {
        myFileService = fileService;
        mySatisfactoryImport = satisfactoryImport;
        myGameVersion = gameVersion;
    }

    public void run() {
        for (Pair<ZipEntry, byte[]> entryPair : mySatisfactoryImport.getImageResources()) {
            myFileService.writeFile(String.format("%s/%s", myGameVersion, entryPair.getFirst().getName()),
                    entryPair.getSecond());
        }
    }
}
