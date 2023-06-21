package net.consolejs.satisfactory.restservice.satisfactoryimport.provider;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestSatisfactoryImportProvider {
    @Mock
    private FormDataMultiPart formData;

    @Mock
    private FormDataBodyPart gameVersionField;

    private SatisfactoryImportProvider importProvider;

    @BeforeEach
    public void setup() {
        importProvider = new SatisfactoryImportProvider(formData);
    }

    @Test
    public void testGetGameVersion() {
        // Given
        String gameVersion = "1.0";
        when(gameVersionField.getValue()).thenReturn(gameVersion);
        when(formData.getField("gameVersion")).thenReturn(gameVersionField);
        when(gameVersionField.getValue()).thenReturn(gameVersion);

        // When
        String result = importProvider.getGameVersion();

        // Then
        assertEquals(gameVersion, result);
    }
}
