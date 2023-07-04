package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemDescriptorDocument;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipe;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipeIngredient;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipeProduct;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.itemdescriptor.ItemDescriptorRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ItemDescriptorImporter implements Runnable {
    private static final float ONE_MINUTE = 60;
    private static final Pattern RECIPE_PRODUCT_PATTERN = Pattern.compile("/Game/FactoryGame/Resource/" +
                                                                                  "(Parts|RawResources)/\\w+/\\w+." +
                                                                                  "(\\w+)\\\"',Amount=([0-9]+)");
    private static final Pattern PRODUCED_IN_PATTERN = Pattern.compile("/Game/FactoryGame/Buildable/Factory/\\w+/\\w+" +
                                                                               ".(\\w+)");
    private static final String RAW_RESOURCES = "RawResources";
    private static final String ALTERNATE_RECIPE_PREFIX = "Alternate: ";
    private final RepositoryFactory myRepositoryFactory;
    private final List<SatisfactoryClassWrapper> myClassWrappers;
    private final String myGameVersion;

    public ItemDescriptorImporter(RepositoryFactory repositoryFactory, List<SatisfactoryClassWrapper> classWrappers,
                                  String gameVersion) {
        myRepositoryFactory = repositoryFactory;
        myClassWrappers = classWrappers;
        myGameVersion = gameVersion;
    }

    public void run() {
        ItemDescriptorRepository repository = myRepositoryFactory.of(ItemDescriptorRepository.class);
        List<ItemRecipe> recipes = getAllRecipes();
        myClassWrappers
                .stream()
                .filter(entry -> NativeClass.FGItemDescriptor.equals(entry.getNativeClass()))
                .flatMap(entry -> entry
                        .getClasses()
                        .stream())
                .forEach(satisfactoryClass -> repository.create(ItemDescriptorDocument
                                                                        .newBuilder()
                                                                        .withGameVersion(myGameVersion)
                                                                        .withClassName(satisfactoryClass.getClassName())
                                                                        .withDisplayName(satisfactoryClass.getDisplayName())
                                                                        .withDescription(satisfactoryClass.getDescription())
                                                                        .withSmallIcon(getCleanIconValue(satisfactoryClass.getSmallIcon()))
                                                                        .withBigIcon(getCleanIconValue(satisfactoryClass.getBigIcon()))
                                                                        .withResourceType(satisfactoryClass.getResourceType())
                                                                        .withRecipes(getRecipesForClass(recipes,
                                                                                                        satisfactoryClass))
                                                                        .build()));
    }

    private List<ItemRecipe> getAllRecipes() {
        return myClassWrappers
                .stream()
                .filter(entry -> NativeClass.FGRecipe.equals(entry.getNativeClass()))
                .flatMap(entry -> entry
                        .getClasses()
                        .stream())
                .map(entry -> ItemRecipe
                        .newBuilder()
                        .withClassName(entry.getClassName())
                        .withDisplayName(entry.getDisplayName())
                        .withIngredients(getRecipeIngredients(entry))
                        .withProduces(getRecipeProducts(entry))
                        .isAlternate(isRecipeAlternate(entry))
                        .withManufacturerClassName(getManufacturerClassName(entry))
                        .build())
                .collect(Collectors.toList());
    }

    private List<ItemRecipe> getRecipesForClass(List<ItemRecipe> recipes, SatisfactoryClass satisfactoryClass) {
        return recipes
                .stream()
                .filter(entry -> isRecipeValidFor(satisfactoryClass, entry))
                .collect(Collectors.toList());
    }

    private String getManufacturerClassName(SatisfactoryClass recipe) {
        Matcher matcher = PRODUCED_IN_PATTERN.matcher(recipe.getProducedIn());
        return matcher.find() ? matcher.group(1) : null;
    }

    private boolean isRecipeAlternate(SatisfactoryClass recipe) {
        return recipe
                .getDisplayName()
                .startsWith(ALTERNATE_RECIPE_PREFIX);
    }

    private boolean isRecipeValidFor(SatisfactoryClass satisfactoryClass, ItemRecipe recipe) {
        return recipe
                .getProduces()
                .stream()
                .anyMatch(entry -> entry
                        .getItemClassName()
                        .equals(satisfactoryClass.getClassName()));
    }

    private List<ItemRecipeIngredient> getRecipeIngredients(SatisfactoryClass recipe) {
        List<ItemRecipeIngredient> ingredients = new ArrayList<>();

        Matcher matcher = RECIPE_PRODUCT_PATTERN.matcher(recipe.getIngredients());
        while (matcher.find()) {
            float perMin = (ONE_MINUTE / recipe.getManufactoringDuration())
                    * Float.parseFloat(matcher.group(3));

            ingredients.add(ItemRecipeIngredient
                                    .newBuilder()
                                    .withItemClassName(matcher.group(2))
                                    .withAmount(Float.parseFloat(matcher.group(3)))
                                    .isResource(RAW_RESOURCES.equals(matcher.group(1)))
                                    .withAmountPerMinute(perMin)
                                    .build());
        }
        return ingredients;
    }

    private List<ItemRecipeProduct> getRecipeProducts(SatisfactoryClass recipe) {
        List<ItemRecipeProduct> products = new ArrayList<>();

        Matcher matcher = RECIPE_PRODUCT_PATTERN.matcher(recipe.getProduct());
        while (matcher.find()) {
            float perMin = (ONE_MINUTE / recipe.getManufactoringDuration())
                    * Float.parseFloat(matcher.group(3));

            products.add(ItemRecipeProduct
                                 .newBuilder()
                                 .withItemClassName(matcher.group(2))
                                 .withAmount(Float.parseFloat(matcher.group(3)))
                                 .isResource(RAW_RESOURCES.equals(matcher.group(1)))
                                 .withAmountPerMinute(perMin)
                                 .build());
        }
        return products;
    }

    private String getCleanIconValue(String icon) {
        if (icon == null) {
            return null;
        }
        return icon
                .replace("Texture2D ", "")
                .split("\\.")[0];
    }
}
