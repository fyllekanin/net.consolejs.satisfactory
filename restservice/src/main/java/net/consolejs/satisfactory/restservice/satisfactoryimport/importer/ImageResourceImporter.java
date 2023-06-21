package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.grizzly.utils.Pair;

import java.util.zip.ZipEntry;

public class ImageResourceImporter {
    private final FileService myFileService;

    public ImageResourceImporter(FileService fileService) {
        myFileService = fileService;
    }

    public void importImageResources(SatisfactoryImport satisfactoryImport, String gameVersion) {
        for (Pair<ZipEntry, byte[]> entryPair : satisfactoryImport.getImageResources()) {
            myFileService.writeFile(String.format("%s/%s", gameVersion, entryPair.getFirst().getName()),
                    entryPair.getSecond());
        }
    }
}
