package net.consolejs.satisfactory.entityview.document.resource;

import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestResourceDocument {

    @Test
    public void shouldBuildCorrect() {
        // Given
        ResourceDocument.Builder builder = ResourceDocument
                .newBuilder()
                .withGameVersion("gameVersion")
                .withClassName("className")
                .withDisplayName("displayName")
                .withDescription("description")
                .withSmallIcon("smallIcon")
                .withBigIcon("bigIcon")
                .withResourceType(ResourceType.RF_SOLID);


        // When
        ResourceDocument document = builder.build();


        // Then
        assertEquals(document.getGameVersion(), "gameVersion");
        assertEquals(document.getClassName(), "className");
        assertEquals(document.getDisplayName(), "displayName");
        assertEquals(document.getDescription(), "description");
        assertEquals(document.getSmallIcon(), "smallIcon");
        assertEquals(document.getBigIcon(), "bigIcon");
        assertEquals(document.getResourceType(), ResourceType.RF_SOLID);
    }

    @Test
    public void testEquals() {
        EqualsVerifier
                .forClass(ResourceDocument.class)
                .usingGetClass()
                .verify();
    }

    private ResourceDocument.Builder getPrefilledBuilder() {
        return ResourceDocument
                .newBuilder()
                .withGameVersion("gameVersion")
                .withClassName("className")
                .withDisplayName("displayName")
                .withDescription("description")
                .withSmallIcon("smallIcon")
                .withBigIcon("bigIcon")
                .withResourceType(ResourceType.RF_SOLID);
    }
}
