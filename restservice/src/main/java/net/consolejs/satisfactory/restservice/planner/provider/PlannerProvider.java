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

        PlannerManufacturer manufacturer = getManufacturer(gameVersion, recipe, document, amount);
        return PlannerStep
                .newBuilder()
                .withRecipeClassName(recipe.getClassName())
                .withAmount(amount)
                .withDisplayName(recipe.getDisplayName())
                .withManufacturer(manufacturer)
                .withPreSteps(getPreSteps(gameVersion, recipe, manufacturer.getAmount()))
                .withIcon(document.getBigIcon())
                .build();
    }

    private PlannerStep getPlannerStepForResource(String gameVersion, ItemRecipeIngredient ingredient,
                                                  float parentManufacturers) {
        ResourceDocument document = myRepositoryFactory
                .of(ResourceRepository.class)
                .findByClassName(gameVersion, ingredient.getItemClassName());

        float amount = ResourceType.RF_LIQUID.equals(document.getResourceType()) ?
                ingredient.getAmountPerMinute() / 1000 :
                ingredient.getAmountPerMinute();
        return getPlannerStep(gameVersion, document, parentManufacturers * amount);
    }

    private PlannerStep getPlannerStepForPart(String gameVersion, ItemRecipeIngredient ingredient, float parentAmount
            , String parentRecipe) {
        ItemDescriptorDocument document = myRepositoryFactory
                .of(ItemDescriptorRepository.class)
                .findByClassName(gameVersion, ingredient.getItemClassName());

        float amount = ResourceType.RF_LIQUID.equals(document.getResourceType()) ?
                parentAmount * (ingredient.getAmountPerMinute() / 1000) :
                parentAmount * ingredient.getAmountPerMinute();
        return getPlannerStep(gameVersion, getItemRecipe(document, parentRecipe), amount,
                              document);
    }

    private PlannerStep getPlannerStep(String gameVersion, ItemRecipe recipe, float amount,
                                       ItemDescriptorDocument document) {
        PlannerManufacturer manufacturer = getManufacturer(gameVersion, recipe, document, amount);
        return PlannerStep
                .newBuilder()
                .withRecipeClassName(recipe.getClassName())
                .withAmount(amount)
                .withDisplayName(recipe.getDisplayName())
                .withManufacturer(manufacturer)
                .withPreSteps(getPreSteps(gameVersion, recipe, manufacturer.getAmount()))
                .build();
    }

    private List<PlannerStep> getPreSteps(String gameVersion, ItemRecipe recipe, float parentManufacturers) {
        return recipe.getIngredients()
                     .stream()
                     .map(ingredient -> ingredient.isResource() ? getPlannerStepForResource(gameVersion, ingredient,
                                                                                            parentManufacturers) :
                             getPlannerStepForPart(gameVersion, ingredient, parentManufacturers,
                                                   recipe.getClassName()))
                     .collect(Collectors.toList());
    }

    private float getAmountProducing(List<ItemRecipeProduct> products, String itemClassName) {
        return products
                .stream()
                .filter(item -> item
                        .getItemClassName()
                        .equals(itemClassName))
                .map(ItemRecipeProduct::getAmountPerMinute)
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

    private PlannerManufacturer getManufacturer(String gameVersion, ItemRecipe recipe,
                                                ItemDescriptorDocument itemDocument,
                                                float amount) {
        ManufacturerDocument document = myRepositoryFactory.of(ManufacturerRepository.class)
                                                           .findByClassName(gameVersion,
                                                                            recipe.getManufacturerClassName());
        PlannerManufacturer.Builder builder = PlannerManufacturer.newBuilder()
                                                                 .withManufacturerClassName(document.getClassName())
                                                                 .withIcon(document.getSmallIcon())
                                                                 .withDisplayName(document.getDisplayName());

        recipe.getProduces()
              .stream()
              .filter(item -> item
                      .getItemClassName()
                      .equals(itemDocument.getClassName()))
              .findFirst()
              .ifPresent(product -> {

                  float perMinute = ResourceType.RF_LIQUID.equals(itemDocument.getResourceType()) ?
                          product.getAmountPerMinute() / 1000 : product.getAmountPerMinute();
                  builder.withAmount(amount / perMinute);
              });
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
