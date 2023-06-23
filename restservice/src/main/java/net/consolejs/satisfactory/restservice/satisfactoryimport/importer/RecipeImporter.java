package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeIngredient;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeProduct;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.recipe.RecipeRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeImporter implements Runnable {
    private static final Pattern PART_RECIPE_PATTERN = Pattern.compile("BlueprintGeneratedClass'\"/Game/FactoryGame/Resource/Parts/[^/]+/[a-zA-Z_]+.([^\"]+)\"',Amount=(\\d+)");
    private static final Pattern RESOURCE_RECIPE_PATTERN = Pattern.compile("BlueprintGeneratedClass'\"/Game/FactoryGame/Resource/RawResources/[^/]+/[a-zA-Z_]+.([^\"]+)\"',Amount=(\\d+)");
    private static final String PRODUCED_IN_START = "/Game/FactoryGame/Buildable/Factory/";
    private static final String IS_ALTERNATE_START = "Alternate: ";
    private final RepositoryFactory myRepositoryFactory;
    private final List<SatisfactoryClassWrapper> myClassWrappers;
    private final String myGameVersion;

    public RecipeImporter(RepositoryFactory repositoryFactory, List<SatisfactoryClassWrapper> classWrappers, String gameVersion) {
        myRepositoryFactory = repositoryFactory;
        myClassWrappers = classWrappers;
        myGameVersion = gameVersion;
    }

    public void run() {
        RecipeRepository repository = myRepositoryFactory.of(RecipeRepository.class);
        myClassWrappers.stream()
                .filter(entry -> NativeClass.FGRecipe.equals(entry.getNativeClass()))
                .findFirst()
                .ifPresent(entry -> {
                    for (SatisfactoryClass clazz : entry.getClasses()) {
                        repository.create(RecipeDocument.newBuilder()
                                .withGameVersion(myGameVersion)
                                .withClassName(clazz.getClassName())
                                .withFullName(clazz.getFullName())
                                .withDisplayName(clazz.getDisplayName())
                                .withIngredients(getRecipeIngredients(clazz))
                                .withDuration(clazz.getManufactoringDuration())
                                .withProducedIn(getProducedIn(clazz))
                                .isAlternate(isAlternate(clazz))
                                .withProducts(getRecipeProducts(clazz))
                                .build());
                    }
                });
    }

    private List<RecipeProduct> getRecipeProducts(SatisfactoryClass satisfactoryClass) {
        if (satisfactoryClass.getProduct() == null) {
            return Collections.emptyList();
        }
        List<RecipeProduct> products = new ArrayList<>();
        products.addAll(getProductFor(satisfactoryClass, true));
        products.addAll(getProductFor(satisfactoryClass, false));
        return products;
    }

    private boolean isAlternate(SatisfactoryClass satisfactoryClass) {
        return satisfactoryClass.getDisplayName().startsWith(IS_ALTERNATE_START);
    }

    private String getProducedIn(SatisfactoryClass satisfactoryClass) {
        if (satisfactoryClass.getProducedIn() == null) {
            return null;
        }
        String[] parts = satisfactoryClass.getProducedIn()
                .replace("(", "")
                .replace(")", "")
                .split(",");
        return Arrays.stream(parts)
                .filter(part -> part.startsWith(PRODUCED_IN_START))
                .map(part -> part.split("\\."))
                .filter(items -> items.length > 1)
                .map(items -> items[1])
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private List<RecipeIngredient> getRecipeIngredients(SatisfactoryClass satisfactoryClass) {
        if (satisfactoryClass.getIngredients() == null) {
            return Collections.emptyList();
        }
        List<RecipeIngredient> ingredients = new ArrayList<>();
        ingredients.addAll(getIngredientsFor(satisfactoryClass, true));
        ingredients.addAll(getIngredientsFor(satisfactoryClass, false));
        return ingredients;
    }

    private List<RecipeProduct> getProductFor(SatisfactoryClass satisfactoryClass, boolean isPart) {
        List<RecipeProduct> products = new ArrayList<>();

        Matcher partMatcher = (isPart ? PART_RECIPE_PATTERN : RESOURCE_RECIPE_PATTERN)
                .matcher(satisfactoryClass.getProduct());
        while (partMatcher.find()) {
            products.add(RecipeProduct.newBuilder()
                    .withDescriptorClassName(partMatcher.group(1))
                    .withAmount(Integer.parseInt(partMatcher.group(2)))
                    .isPart(isPart)
                    .build());
        }
        return products;
    }

    private List<RecipeIngredient> getIngredientsFor(SatisfactoryClass satisfactoryClass, boolean isPart) {
        List<RecipeIngredient> ingredients = new ArrayList<>();

        Matcher partMatcher = (isPart ? PART_RECIPE_PATTERN : RESOURCE_RECIPE_PATTERN)
                .matcher(satisfactoryClass.getIngredients());
        while (partMatcher.find()) {
            String className = getRecipeClassName(partMatcher.group(1), isPart);
            if (className == null || satisfactoryClass.getClassName().equals(className)) {
                continue;
            }
            ingredients.add(RecipeIngredient.newBuilder()
                    .withClassName(className)
                    .withDescriptorClassName(partMatcher.group(1))
                    .withAmount(Integer.parseInt(partMatcher.group(2)))
                    .isPart(isPart)
                    .withResourceType(getResourceType(partMatcher.group(1)))
                    .build());
        }
        return ingredients;
    }

    private ResourceType getResourceType(String descriptorClassName) {
        return myClassWrappers.stream()
                .filter(entry -> NativeClass.FGResourceDescriptor.equals(entry.getNativeClass()))
                .flatMap(entry -> entry.getClasses().stream())
                .filter(entry -> descriptorClassName.equals(entry.getClassName()))
                .map(SatisfactoryClass::getResourceType)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private String getRecipeClassName(String descriptorClassName, boolean isPart) {
        return myClassWrappers.stream()
                .filter(entry -> NativeClass.FGRecipe.equals(entry.getNativeClass()))
                .flatMap(entry -> entry.getClasses().stream())
                .filter(entry -> isRecipeProducing(entry, descriptorClassName, isPart))
                .filter(entry -> !isAlternate(entry))
                .map(SatisfactoryClass::getClassName)
                .findFirst()
                .orElse(null);
    }

    private boolean isRecipeProducing(SatisfactoryClass satisfactoryClass, String descriptorClassName, boolean isPart) {
        if (satisfactoryClass.getProduct() == null) {
            return false;
        }
        Matcher matcher = (isPart ? PART_RECIPE_PATTERN : RESOURCE_RECIPE_PATTERN)
                .matcher(satisfactoryClass.getProduct());
        while (matcher.find()) {
            if (descriptorClassName.equals(matcher.group(1))) {
                return true;
            }
        }
        return false;
    }
}
