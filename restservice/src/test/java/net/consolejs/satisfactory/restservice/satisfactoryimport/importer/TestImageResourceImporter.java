package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.grizzly.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestImageResourceImporter {
    @Mock
    private FileService fileService;
    @Mock
    private SatisfactoryImport satisfactoryImport;
    @Mock
    private ZipEntry zipEntry;

    private ImageResourceImporter imageResourceImporter;

    @BeforeEach
    public void setup() {
        imageResourceImporter = new ImageResourceImporter(fileService);
    }

    @Test
    public void testImportImageResources() {
        // Given
        String gameVersion = "1.0";
        byte[] imageData = new byte[]{0x12, 0x34, 0x56};
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();
        imageResources.add(new Pair<>(zipEntry, imageData));
        when(satisfactoryImport.getImageResources()).thenReturn(imageResources);
        when(zipEntry.getName()).thenReturn("image.png");

        // When
        imageResourceImporter.importImageResources(satisfactoryImport, gameVersion);

        // Then
        verify(fileService).writeFile("1.0/image.png", imageData);
        // Add additional verifications as needed
    }
}
