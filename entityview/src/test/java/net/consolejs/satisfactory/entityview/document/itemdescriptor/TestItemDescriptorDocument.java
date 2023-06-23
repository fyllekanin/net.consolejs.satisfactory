package net.consolejs.satisfactory.entityview.document.itemdescriptor;

import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItemDescriptorDocument {

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

        ItemRecipe recipe = ItemRecipe
                .newBuilder()
                .withClassName("recipeClassName")
                .withDisplayName("recipeDisplayName")
                .withIngredients(ingredients)
                .withProduces(products)
                .isAlternate(true)
                .withManufacturerClassName("recipeManufacturerClassName")
                .build();

        List<ItemRecipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        ItemDescriptorDocument.Builder builder = ItemDescriptorDocument
                .newBuilder()
                .withGameVersion("gameVersion")
                .withClassName("className")
                .withDisplayName("displayName")
                .withDescription("description")
                .withSmallIcon("smallIcon")
                .withBigIcon("bigIcon")
                .withResourceType(ResourceType.RF_SOLID)
                .withRecipes(recipes);

        // When
        ItemDescriptorDocument document = builder.build();

        // Then
        assertEquals("gameVersion", document.getGameVersion());
        assertEquals("className", document.getClassName());
        assertEquals("displayName", document.getDisplayName());
        assertEquals("description", document.getDescription());
        assertEquals("smallIcon", document.getSmallIcon());
        assertEquals("bigIcon", document.getBigIcon());
        assertEquals(ResourceType.RF_SOLID, document.getResourceType());
        assertEquals(recipes, document.getRecipes());
    }

    @Test
    public void testEquals() {
        EqualsVerifier
                .forClass(ItemDescriptorDocument.class)
                .usingGetClass()
                .verify();
    }
}