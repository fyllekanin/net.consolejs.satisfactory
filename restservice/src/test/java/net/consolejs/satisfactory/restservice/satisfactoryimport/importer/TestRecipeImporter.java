package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.recipe.RecipeDocument;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.recipes.RecipeRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestRecipeImporter {
    @Mock
    private RepositoryFactory repositoryFactory;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private SatisfactoryClassWrapper classWrapper;
    @Mock
    private SatisfactoryClass recipeClass;

    private RecipeImporter recipeImporter;

    @BeforeEach
    public void setup() {
        when(repositoryFactory.of(RecipeRepository.class)).thenReturn(recipeRepository);
        recipeImporter = new RecipeImporter(repositoryFactory);
    }

    @Test
    public void testImportRecipesNoClassWrapper() {
        // Given
        List<SatisfactoryClassWrapper> classWrappers = Collections.emptyList();
        String gameVersion = "1.0";

        // When
        recipeImporter.importRecipes(classWrappers, gameVersion);

        // Then
        verifyNoInteractions(recipeRepository);
    }

    @Test
    public void testImportRecipesNoMatchingClassWrapper() {
        // Given
        SatisfactoryClassWrapper nonRecipeClassWrapper = mock(SatisfactoryClassWrapper.class);
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(nonRecipeClassWrapper);
        String gameVersion = "1.0";

        // When
        recipeImporter.importRecipes(classWrappers, gameVersion);

        // Then
        verifyNoInteractions(recipeRepository);
    }

    @Test
    public void testImportRecipesOneMatchingClassWrapperNoClasses() {
        // Given
        when(classWrapper.getNativeClass()).thenReturn(NativeClass.FGRecipe);
        when(classWrapper.getClasses()).thenReturn(Collections.emptyList());
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(classWrapper);
        String gameVersion = "1.0";

        // When
        recipeImporter.importRecipes(classWrappers, gameVersion);

        // Then
        verifyNoInteractions(recipeRepository);
    }

    @Test
    public void testImportRecipesOneMatchingClassWrapperOneClass() {
        // Given
        when(classWrapper.getNativeClass()).thenReturn(NativeClass.FGRecipe);
        List<SatisfactoryClass> classes = Collections.singletonList(recipeClass);
        when(classWrapper.getClasses()).thenReturn(classes);
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(classWrapper);
        String gameVersion = "1.0";

        // When
        recipeImporter.importRecipes(classWrappers, gameVersion);

        // Then
        verify(recipeRepository).create(any(RecipeDocument.class));
        verify(recipeClass).getClassName();
        verify(recipeClass).getFullName();
        verify(recipeClass).getDisplayName();
        verify(recipeClass).getManufactoringDuration();
        verify(recipeClass).getIngredients();
    }

    @Test
    public void testImportRecipesOneMatchingClassWrapperOneClassWithIngredients() {
        // Given
        when(classWrapper.getNativeClass()).thenReturn(NativeClass.FGRecipe);
        List<SatisfactoryClass> classes = Collections.singletonList(recipeClass);
        when(classWrapper.getClasses()).thenReturn(classes);
        when(recipeClass.getIngredients()).thenReturn("((ItemClass=BlueprintGeneratedClass'\"/Game/FactoryGame/Resource/Parts/CopperSheet/Desc_CopperSheet.Desc_CopperSheet_C\"',Amount=2),(ItemClass=BlueprintGeneratedClass'\"/Game/FactoryGame/Resource/Parts/Plastic/Desc_Plastic.Desc_Plastic_C\"',Amount=4))");
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(classWrapper);
        String gameVersion = "1.0";

        // When
        recipeImporter.importRecipes(classWrappers, gameVersion);

        // Then
        verify(recipeClass).getClassName();
        verify(recipeClass).getFullName();
        verify(recipeClass).getDisplayName();
        verify(recipeClass).getManufactoringDuration();
        verify(recipeClass, times(2)).getIngredients();
        verify(recipeRepository).create(any(RecipeDocument.class));
    }
}
