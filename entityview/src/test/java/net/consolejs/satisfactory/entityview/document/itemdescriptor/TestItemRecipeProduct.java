package net.consolejs.satisfactory.entityview.document.itemdescriptor;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestItemRecipeProduct {

    @Test
    public void shouldBuildCorrect() {
        // Given
        ItemRecipeProduct.Builder builder = ItemRecipeProduct
                .newBuilder()
                .withItemClassName("itemClassName")
                .withAmount(10.0f)
                .isResource(true);

        // When
        ItemRecipeProduct product = builder.build();

        // Then
        assertEquals("itemClassName", product.getItemClassName());
        assertEquals(10.0f, product.getAmount(), 0.0f);
        assertTrue(product.isResource());
    }

    @Test
    public void testEquals() {
        EqualsVerifier
                .forClass(ItemRecipeProduct.class)
                .usingGetClass()
                .verify();
    }
}
