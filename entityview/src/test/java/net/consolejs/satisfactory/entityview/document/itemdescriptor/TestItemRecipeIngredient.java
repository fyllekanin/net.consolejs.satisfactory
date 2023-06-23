package net.consolejs.satisfactory.entityview.document.itemdescriptor;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestItemRecipeIngredient {

    @Test
    public void shouldBuildCorrect() {
        // Given
        ItemRecipeIngredient.Builder builder = ItemRecipeIngredient
                .newBuilder()
                .withItemClassName("itemClassName")
                .withAmount(5.0f)
                .isResource(false);

        // When
        ItemRecipeIngredient ingredient = builder.build();

        // Then
        assertEquals("itemClassName", ingredient.getItemClassName());
        assertEquals(5.0f, ingredient.getAmount(), 0.0f);
        assertFalse(ingredient.isResource());
    }

    @Test
    public void testEquals() {
        EqualsVerifier
                .forClass(ItemRecipeIngredient.class)
                .usingGetClass()
                .verify();
    }
}