package net.consolejs.satisfactory.restservice.planner.provider;

import net.consolejs.satisfactory.entityview.document.extractor.ExtractorDocument;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemDescriptorDocument;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipe;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipeIngredient;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipeProduct;
import net.consolejs.satisfactory.entityview.document.manufacturer.ManufacturerDocument;
import net.consolejs.satisfactory.entityview.document.resource.ResourceDocument;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.extractor.ExtractorRepository;
import net.consolejs.satisfactory.repository.itemdescriptor.ItemDescriptorRepository;
import net.consolejs.satisfactory.repository.manufacturer.ManufacturerRepository;
import net.consolejs.satisfactory.repository.resource.ResourceRepository;
import net.consolejs.satisfactory.restservice.planner.model.PlannerExtractor;
import net.consolejs.satisfactory.restservice.planner.model.PlannerManufacturer;
import net.consolejs.satisfactory.restservice.planner.model.PlannerStep;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlannerProvider {
    private final RepositoryFactory myRepositoryFactory;

    public PlannerProvider(RepositoryFactory repositoryFactory) {
        myRepositoryFactory = repositoryFactory;
    }

    public boolean doesItemExist(String gameVersion, String className) {
        return myRepositoryFactory
                .of(ItemDescriptorRepository.class)
                .doItemExistByClassName(gameVersion, className);
    }

    public PlannerStep getSolution(String gameVersion, String itemClassName, float amount, String recipeClassName) {
        ItemDescriptorDocument document = myRepositoryFactory
                .of(ItemDescriptorRepository.class)
                .findByClassName(gameVersion, itemClassName);
        ItemRecipe recipe = getItemRecipe(document, null, recipeClassName);

        return PlannerStep
                .newBuilder()
                .withRecipeClassName(recipe.getClassName())
                .withAmount(amount)
                .withDisplayName(recipe.getDisplayName())
                .withManufacturer(getManufacturer(gameVersion, recipe, itemClassName, amount))
                .withPreSteps(getPreSteps(gameVersion, recipe, amount, itemClassName))
                .withIcon(document.getBigIcon())
                .build();
    }

    private PlannerStep getPlannerStepForResource(String gameVersion, ItemRecipeIngredient ingredient,
                                                  float parentAmount) {
        ResourceDocument document = myRepositoryFactory
                .of(ResourceRepository.class)
                .findByClassName(gameVersion, ingredient.getItemClassName());

        return getPlannerStep(gameVersion, document, ingredient.getAmount() * parentAmount);
    }

    private PlannerStep getPlannerStepForPart(String gameVersion, ItemRecipeIngredient ingredient, float parentAmount
            , String parentRecipe) {
        ItemDescriptorDocument document = myRepositoryFactory
                .of(ItemDescriptorRepository.class)
                .findByClassName(gameVersion, ingredient.getItemClassName());

        float amount = ResourceType.RF_LIQUID.equals(document.getResourceType()) ?
                ingredient.getAmount() / 1000 : ingredient.getAmount();
        return getPlannerStep(gameVersion, getItemRecipe(document, parentRecipe), amount * parentAmount,
                              document.getClassName());
    }

    private PlannerStep getPlannerStep(String gameVersion, ItemRecipe recipe, float amount, String itemClassName) {
        return PlannerStep
                .newBuilder()
                .withRecipeClassName(recipe.getClassName())
                .withAmount(amount)
                .withDisplayName(recipe.getDisplayName())
                .withManufacturer(getManufacturer(gameVersion, recipe, itemClassName, amount))
                .withPreSteps(getPreSteps(gameVersion, recipe, amount, itemClassName))
                .build();
    }

    private List<PlannerStep> getPreSteps(String gameVersion, ItemRecipe recipe, float parentAmount,
                                          String itemClassName) {
        return recipe
                .getIngredients()
                .stream()
                .map(ingredient -> {

                    float producing = getAmountProducing(recipe.getProduces(), itemClassName);
                    return ingredient.isResource() ? getPlannerStepForResource(gameVersion, ingredient,
                                                                               parentAmount / producing) :
                            getPlannerStepForPart(gameVersion, ingredient, parentAmount / producing,
                                                  recipe.getClassName());
                })
                .collect(Collectors.toList());
    }

    private float getAmountProducing(List<ItemRecipeProduct> products, String itemClassName) {
        return products
                .stream()
                .filter(item -> item
                        .getItemClassName()
                        .equals(itemClassName))
                .map(ItemRecipeProduct::getAmount)
                .findFirst()
                .orElse(1F);
    }

    private PlannerStep getPlannerStep(String gameVersion, ResourceDocument resourceDocument, float amount) {
        return PlannerStep
                .newBuilder()
                .withRecipeClassName(resourceDocument.getClassName())
                .withAmount(amount)
                .withDisplayName(resourceDocument.getDisplayName())
                .withExtractor(getExtractor(gameVersion, resourceDocument))
                .build();
    }

    private PlannerExtractor getExtractor(String gameVersion, ResourceDocument resourceDocument) {
        List<ExtractorDocument> documents = myRepositoryFactory.of(ExtractorRepository.class)
                                                               .getExtractorsByGameVersion(gameVersion);

        return documents.stream()
                        .filter(item -> item.getResourceType()
                                            .equals(resourceDocument.getResourceType()))
                        .filter(item -> item.getAllowedResources()
                                            .size() == 0 || item.getAllowedResources()
                                                                .contains(resourceDocument.getClassName()))
                        .map(item -> PlannerExtractor.newBuilder()
                                                     .withExtractorClassName(item.getClassName())
                                                     .withDisplayName(item.getDisplayName())
                                                     .withIcon(item.getSmallIcon())
                                                     .build())
                        .findFirst()
                        .orElse(null);
    }

    private PlannerManufacturer getManufacturer(String gameVersion, ItemRecipe recipe, String itemClassName,
                                                float amount) {
        ManufacturerDocument document = myRepositoryFactory.of(ManufacturerRepository.class)
                                                           .findByClassName(gameVersion,
                                                                            recipe.getManufacturerClassName());
        PlannerManufacturer.Builder builder = PlannerManufacturer
                .newBuilder()
                .withManufacturerClassName(document.getClassName())
                .withIcon(document.getSmallIcon())
                .withDisplayName(document.getDisplayName());

        recipe
                .getProduces()
                .stream()
                .filter(item -> item
                        .getItemClassName()
                        .equals(itemClassName))
                .findFirst()
                .ifPresent(product -> builder.withAmount(amount / product.getAmountPerMinute()));
        return builder.build();
    }

    private ItemRecipe getItemRecipe(ItemDescriptorDocument document, String parentRecipe) {
        return getItemRecipe(document, parentRecipe, null);
    }

    private ItemRecipe getItemRecipe(ItemDescriptorDocument document, String parentRecipe, String recipeClassName) {
        if (recipeClassName != null) {
            return document
                    .getRecipes()
                    .stream()
                    .filter(item -> !item.getClassName()
                                         .equals(parentRecipe))
                    .filter(item -> item.getClassName()
                                        .equals(recipeClassName))
                    .min(Comparator.comparingInt(ItemRecipe::getProducesSize))
                    .orElse(null);
        }

        return document
                .getRecipes()
                .stream()
                .filter(item -> !item.getClassName()
                                     .equals(parentRecipe))
                .filter(item -> !item.isAlternate())
                .min(Comparator.comparingInt(ItemRecipe::getProducesSize))
                .orElse(null);
    }
}
