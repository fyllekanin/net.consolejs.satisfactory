package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.recipe.RecipeRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.restservice.satisfactoryimport.provider.SatisfactoryImportProvider;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.IOException;

public class SatisfactoryImporter {
    private final FileService myFileService;
    private final RepositoryFactory myRepositoryFactory;

    public SatisfactoryImporter(FileService fileService, RepositoryFactory repositoryFactory) {
        myFileService = fileService;
        myRepositoryFactory = repositoryFactory;
    }

    public boolean isAlreadyImported(FormDataMultiPart formParams) {
        SatisfactoryImportProvider provider = new SatisfactoryImportProvider(formParams);
        return myRepositoryFactory.of(RecipeRepository.class)
                .isAnyRecipeForGameVersion(provider.getGameVersion());
    }

    public boolean isAlreadyImported(String gameVersion) {
        return myRepositoryFactory.of(RecipeRepository.class)
                .isAnyRecipeForGameVersion(gameVersion);
    }

    public void run(FormDataMultiPart formParams) throws IOException, InterruptedException {
        SatisfactoryImportProvider provider = new SatisfactoryImportProvider(formParams);

        SatisfactoryImport satisfactoryImport = provider.getSatisfactoryImport();
        Thread imageResourceImporter = new Thread(new ImageResourceImporter(myFileService, satisfactoryImport, provider.getGameVersion()));
        Thread recipeImporter = new Thread(new RecipeImporter(myRepositoryFactory, provider.getSatisfactoryClassWrappers(satisfactoryImport), provider.getGameVersion()));
        Thread resourceImporter = new Thread(new ResourceImporter(myRepositoryFactory, provider.getSatisfactoryClassWrappers(satisfactoryImport), provider.getGameVersion()));

        imageResourceImporter.start();
        recipeImporter.start();
        resourceImporter.start();

        imageResourceImporter.join();
        recipeImporter.join();
        resourceImporter.join();
    }
}