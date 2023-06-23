package net.consolejs.satisfactory.restservice.planner.provider;

import net.consolejs.satisfactory.entityview.document.manufacturer.ManufacturerDocument;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeIngredient;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeProduct;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.manufacturer.ManufacturerRepository;
import net.consolejs.satisfactory.repository.recipe.RecipeRepository;
import net.consolejs.satisfactory.restservice.planner.model.PlannerManufacturer;
import net.consolejs.satisfactory.restservice.planner.model.PlannerStep;

import java.util.List;
import java.util.stream.Collectors;

public class PlannerProvider {
    private final RepositoryFactory myRepositoryFactory;

    public PlannerProvider(RepositoryFactory repositoryFactory) {
        myRepositoryFactory = repositoryFactory;
    }

    public PlannerStep getSolution(String gameVersion, String recipeClass, float amount) {
        RecipeDocument recipeDocument = myRepositoryFactory.of(RecipeRepository.class)
                .findByClassName(gameVersion, recipeClass);
        if (recipeDocument == null) {
            return null;
        }
        float produces = getProducesAmount(recipeDocument);

        return PlannerStep.newBuilder()
                .withRecipeClassName(recipeDocument.getClassName())
                .withAmount(amount)
                .withManufacturer(getManufacturer(gameVersion, recipeDocument))
                .withPreSteps(getPreSteps(gameVersion, recipeDocument, amount / produces))
                .build();
    }

    private List<PlannerStep> getPreSteps(String gameVersion, RecipeDocument recipeDocument, float amount) {
        return recipeDocument.getIngredients().stream()
                .map(ingredient -> getSolution(gameVersion, ingredient.getClassName(), getAmount(ingredient, amount)))
                .collect(Collectors.toList());
    }

    private float getAmount(RecipeIngredient ingredient, float amount) {


        if (ResourceType.RF_LIQUID.equals(ingredient.getResourceType())) {
            return (ingredient.getAmount() * amount) / 1000;
        }
        return ingredient.getAmount() * amount;
    }

    private float getProducesAmount(RecipeDocument recipeDocument) {
        String descriptorClassName = recipeDocument.getClassName().replace("Recipe_", "Desc_");
        return recipeDocument.getProducts().stream()
                .filter(entry -> entry.getDescriptorClassName().equals(descriptorClassName))
                .map(RecipeProduct::getAmount)
                .findFirst()
                .orElse(1F);
    }

    private PlannerManufacturer getManufacturer(String gameVersion, RecipeDocument recipeDocument) {
        ManufacturerDocument document = myRepositoryFactory.of(ManufacturerRepository.class)
                .findByClassName(gameVersion, recipeDocument.getProducedIn());
        if (document == null) {
            return null;
        }

        return PlannerManufacturer.newBuilder()
                .withManufacturerClassName(document.getClassName())
                .withIcon(document.getSmallIcon())
                .withDisplayName(document.getDisplayName())
                .build();
    }
}
