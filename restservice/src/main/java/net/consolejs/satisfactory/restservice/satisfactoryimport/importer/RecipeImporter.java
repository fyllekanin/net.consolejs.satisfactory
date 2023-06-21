package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeIngredient;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.recipes.RecipeRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeImporter {
    private static final Pattern RECIPE_PATTERN = Pattern.compile("BlueprintGeneratedClass'\"/Game/FactoryGame/Resource/Parts/[^/]+/[a-zA-Z_]+.([^\"]+)\"',Amount=(\\d+)");
    private final RepositoryFactory myRepositoryFactory;

    public RecipeImporter(RepositoryFactory repositoryFactory) {
        myRepositoryFactory = repositoryFactory;
    }

    public void importRecipes(List<SatisfactoryClassWrapper> classWrappers, String gameVersion) {
        RecipeRepository repository = myRepositoryFactory.of(RecipeRepository.class);
        classWrappers.stream()
                .filter(entry -> NativeClass.FGRecipe.equals(entry.getNativeClass()))
                .findFirst()
                .ifPresent(entry -> {
                    for (SatisfactoryClass clazz : entry.getClasses()) {
                        repository.create(RecipeDocument.newBuilder()
                                .withGameVersion(gameVersion)
                                .withClassName(clazz.getClassName())
                                .withFullName(clazz.getFullName())
                                .withDisplayName(clazz.getDisplayName())
                                .withIngredients(getRecipeIngredients(clazz))
                                .withDuration(clazz.getManufactoringDuration())
                                .build());
                    }
                });
    }

    private List<RecipeIngredient> getRecipeIngredients(SatisfactoryClass satisfactoryClass) {
        if (satisfactoryClass.getIngredients() == null) {
            return Collections.emptyList();
        }
        Matcher matcher = RECIPE_PATTERN.matcher(satisfactoryClass.getIngredients());
        List<RecipeIngredient> ingredients = new ArrayList<>();
        while (matcher.find()) {
            ingredients.add(RecipeIngredient.newBuilder()
                    .withClassName(matcher.group(1))
                    .withAmount(Integer.parseInt(matcher.group(2)))
                    .build());
        }
        return ingredients;
    }
}