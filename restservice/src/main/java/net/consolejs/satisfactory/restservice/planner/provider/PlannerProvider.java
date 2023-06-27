package net.consolejs.satisfactory.restservice.planner.provider;

import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemDescriptorDocument;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipe;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipeIngredient;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipeProduct;
import net.consolejs.satisfactory.entityview.document.manufacturer.ManufacturerDocument;
import net.consolejs.satisfactory.entityview.document.resource.ResourceDocument;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.itemdescriptor.ItemDescriptorRepository;
import net.consolejs.satisfactory.repository.manufacturer.ManufacturerRepository;
import net.consolejs.satisfactory.repository.resource.ResourceRepository;
import net.consolejs.satisfactory.restservice.planner.model.PlannerManufacturer;
import net.consolejs.satisfactory.restservice.planner.model.PlannerStep;

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

    public PlannerStep getSolution(String gameVersion, String itemClassName, float amount) {
        ItemDescriptorDocument document = myRepositoryFactory
                .of(ItemDescriptorRepository.class)
                .findByClassName(gameVersion, itemClassName);
        ItemRecipe recipe = getItemRecipe(document);

        return getPlannerStep(gameVersion, recipe, amount, document.getClassName());
    }

    private PlannerStep getPlannerStepForResource(String gameVersion, ItemRecipeIngredient ingredient,
                                                  float parentAmount) {
        ResourceDocument document = myRepositoryFactory
                .of(ResourceRepository.class)
                .findByClassName(gameVersion, ingredient.getItemClassName());

        float amount = ResourceType.RF_LIQUID.equals(document.getResourceType()) ?
                ingredient.getAmount() / 1000 : ingredient.getAmount();
        return getPlannerStep(gameVersion, document, amount * parentAmount);
    }

    private PlannerStep getPlannerStepForPart(String gameVersion, ItemRecipeIngredient ingredient, float parentAmount) {
        ItemDescriptorDocument document = myRepositoryFactory
                .of(ItemDescriptorRepository.class)
                .findByClassName(gameVersion, ingredient.getItemClassName());

        return getPlannerStep(gameVersion, getItemRecipe(document), ingredient.getAmount() * parentAmount,
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
                            getPlannerStepForPart(gameVersion, ingredient, parentAmount / producing);
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
                .withManufacturer(null)
                .build();
    }

    private PlannerManufacturer getManufacturer(String gameVersion, ItemRecipe recipe, String itemClassName,
                                                float amount) {
        ManufacturerDocument document = myRepositoryFactory
                .of(ManufacturerRepository.class)
                .findByClassName(gameVersion, recipe.getManufacturerClassName());
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

    private ItemRecipe getItemRecipe(ItemDescriptorDocument document) {
        return document
                .getRecipes()
                .stream()
                .filter(item -> !item.isAlternate())
                .findFirst()
                .orElse(null);
    }
}
