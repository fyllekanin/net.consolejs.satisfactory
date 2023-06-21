package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSatisfactoryClass {

    @Test
    public void testShouldEqual() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();

        // When
        SatisfactoryClass first = builder.build();
        SatisfactoryClass second = first.newBuilderFromCurrent()
                .build();

        // Then
        assertEquals(first, second);
    }

    @Test
    public void testShouldNotEqual() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();

        // When
        SatisfactoryClass first = builder.build();
        SatisfactoryClass second = first.newBuilderFromCurrent()
                .withFullName("different")
                .build();

        // Then
        assertNotEquals(first, second);
    }

    @Test
    public void testShouldEqualIfSameObject() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();
        SatisfactoryClass clazz = builder.build();

        // When
        boolean result = clazz.equals(clazz);

        // Then
        assertTrue(result);
    }

    @Test
    public void testShouldNotEqualIfNull() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();
        SatisfactoryClass clazz = builder.build();

        // When
        boolean result = clazz.equals(null);

        // Then
        assertFalse(result);
    }

    @Test
    public void testShouldHaveSameHashCode() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();
        SatisfactoryClass first = builder.build();
        SatisfactoryClass second = builder.build();


        // When Then
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testShouldNotHaveSameHashCode() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();
        SatisfactoryClass first = builder.build();
        SatisfactoryClass second = builder.withDisplayName("different").build();


        // When Then
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testBuilder() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();

        // When
        SatisfactoryClass obj = builder.build();

        // Then
        assertNotNull(obj);
        assertEquals("ClassName", obj.getClassName());
        assertEquals("FullName", obj.getFullName());
        assertEquals("DisplayName", obj.getDisplayName());
        assertEquals(5, obj.getManufactoringDuration());
        assertEquals("Ingredients", obj.getIngredients());
    }

    private SatisfactoryClass.Builder getBuilderWithAllPropertiesFilled() {
        return SatisfactoryClass.newBuilder()
                .withClassName("ClassName")
                .withFullName("FullName")
                .withDisplayName("DisplayName")
                .withManufactoringDuration(5)
                .withIngredients("Ingredients");
    }
}
