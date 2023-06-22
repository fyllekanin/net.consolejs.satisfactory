package net.consolejs.satisfactory.service.file;

import org.jvnet.hk2.annotations.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileService {
    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());
    private static final String FILE_PATH_KEY = "SATISFACTORY_RESOURCE_PATH";
    private final String myRootPath;

    public FileService() {
        myRootPath = System.getProperty(FILE_PATH_KEY);
    }

    public void deleteDirectory(String path) {
        String directoryPath = String.format("%s/%s", myRootPath, path);
        File directory = new File(directoryPath);
        File[] content = directory.listFiles();

        if (content != null) {
            for (File file : content) {
                deleteDirectory(file.getPath().replace(myRootPath, ""));
            }
        }

        directory.delete();
    }

    public void writeFile(String path, byte[] bytes) {
        LOGGER.log(Level.FINE, String.format("Starting to write file: \"%s\"", path));
        String directoryPath = String.format("%s/%s", myRootPath, getDirectoryPath(path));
        try {
            Files.createDirectories(Paths.get(directoryPath));
            File file = new File(String.format("%s/%s", myRootPath, path));
            if (file.exists()) {
                LOGGER.log(Level.FINE, String.format("File already exists: \"%s\"", path));
                return;
            }

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();

            LOGGER.log(Level.FINE, String.format("Finished to write file: \"%s\"", path));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, String.format("Failed to write file: \"%s\", because: %s", path, exception.getMessage()));
        }
    }

    private String getDirectoryPath(String path) {
        String[] parts = path.split("/");
        return String.join("/", Arrays.copyOf(parts, parts.length - 1));
    }
}
