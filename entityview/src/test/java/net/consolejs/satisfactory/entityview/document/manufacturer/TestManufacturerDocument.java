package net.consolejs.satisfactory.entityview.document.manufacturer;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestManufacturerDocument {

    @Test
    public void shouldBuildCorrect() {
        // Given
        ManufacturerDocument.Builder builder = ManufacturerDocument
                .newBuilder()
                .withGameVersion("gameVersion")
                .withClassName("className")
                .withDisplayName("displayName")
                .withDescription("description")
                .withSmallIcon("smallIcon")
                .withBigIcon("bigIcon");

        // When
        ManufacturerDocument document = builder.build();

        // Then
        assertEquals(document.getGameVersion(), "gameVersion");
        assertEquals(document.getClassName(), "className");
        assertEquals(document.getDisplayName(), "displayName");
        assertEquals(document.getDescription(), "description");
        assertEquals(document.getSmallIcon(), "smallIcon");
        assertEquals(document.getBigIcon(), "bigIcon");
    }

    @Test
    public void testEquals() {
        EqualsVerifier
                .forClass(ManufacturerDocument.class)
                .usingGetClass()
                .verify();
    }
}
