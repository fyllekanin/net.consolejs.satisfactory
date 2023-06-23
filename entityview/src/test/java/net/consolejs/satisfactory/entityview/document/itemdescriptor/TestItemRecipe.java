package net.consolejs.satisfactory.entityview.document.itemdescriptor;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestItemRecipe {

    @Test
    public void shouldBuildCorrect() {
        // Given
        ItemRecipeIngredient ingredient1 = ItemRecipeIngredient
                .newBuilder()
                .withItemClassName("item1")
                .withAmount(5.0f)
                .isResource(false)
                .build();

        ItemRecipeIngredient ingredient2 = ItemRecipeIngredient
                .newBuilder()
                .withItemClassName("item2")
                .withAmount(10.0f)
                .isResource(false)
                .build();

        ItemRecipeProduct product1 = ItemRecipeProduct
                .newBuilder()
                .withItemClassName("item3")
                .withAmount(2.0f)
                .isResource(false)
                .build();

        ItemRecipeProduct product2 = ItemRecipeProduct
                .newBuilder()
                .withItemClassName("item4")
                .withAmount(4.0f)
                .isResource(true)
                .build();

        List<ItemRecipeIngredient> ingredients = Arrays.asList(ingredient1, ingredient2);
        List<ItemRecipeProduct> products = Arrays.asList(product1, product2);

        ItemRecipe.Builder builder = ItemRecipe
                .newBuilder()
                .withClassName("className")
                .withDisplayName("displayName")
                .withIngredients(ingredients)
                .withProduces(products)
                .isAlternate(true)
                .withManufacturerClassName("manufacturerClassName");

        // When
        ItemRecipe recipe = builder.build();

        // Then
        assertEquals("className", recipe.getClassName());
        assertEquals("displayName", recipe.getDisplayName());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(products, recipe.getProduces());
        assertTrue(recipe.isAlternate());
        assertEquals("manufacturerClassName", recipe.getManufacturerClassName());
    }

    @Test
    public void testEquals() {
        EqualsVerifier
                .forClass(ItemRecipe.class)
                .usingGetClass()
                .verify();
    }
}