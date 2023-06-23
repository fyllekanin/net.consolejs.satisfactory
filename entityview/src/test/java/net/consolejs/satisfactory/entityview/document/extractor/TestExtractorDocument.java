package net.consolejs.satisfactory.entityview.document.extractor;

import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExtractorDocument {

    @Test
    public void shouldBuildCorrect() {
        // Given
        List<String> allowedResources = Arrays.asList("resource1", "resource2", "resource3");

        ExtractorDocument.Builder builder = ExtractorDocument
                .newBuilder()
                .withGameVersion("gameVersion")
                .withClassName("className")
                .withResourceType(ResourceType.RF_SOLID)
                .withDisplayName("displayName")
                .withDescription("description")
                .withAllowedResources(allowedResources)
                .withSmallIcon("smallIcon")
                .withBigIcon("bigIcon");

        // When
        ExtractorDocument document = builder.build();

        // Then
        assertEquals("gameVersion", document.getGameVersion());
        assertEquals("className", document.getClassName());
        assertEquals(ResourceType.RF_SOLID, document.getResourceType());
        assertEquals("displayName", document.getDisplayName());
        assertEquals("description", document.getDescription());
        assertEquals(allowedResources, document.getAllowedResources());
        assertEquals("smallIcon", document.getSmallIcon());
        assertEquals("bigIcon", document.getBigIcon());
    }

    @Test
    public void testEquals() {
        EqualsVerifier
                .forClass(ExtractorDocument.class)
                .usingGetClass()
                .verify();
    }
}