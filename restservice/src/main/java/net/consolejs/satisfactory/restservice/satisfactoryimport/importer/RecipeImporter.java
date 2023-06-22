package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import net.consolejs.satisfactory.entityview.document.recipe.RecipeIngredient;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
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
    private static final Pattern RECIPE_PATTERN = Pattern.compile("BlueprintGeneratedClass'\"/Game/FactoryGame/Resource/Parts/[^/]+/[a-zA-Z_]+.([^\"]+)\"',Amount=(\\d+)");
    private static final Pattern PRODUCED_IN_TO_CLASS_NAME = Pattern.compile(".*/(.*\\\\.(.*_C))");
    private static final String PRODUCED_IN_START = "/Game/FactoryGame/Buildable/Factory/";
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
                                .build());
                    }
                });
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
