package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.itemdescriptor.ItemDescriptorRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.restservice.satisfactoryimport.provider.SatisfactoryImportProvider;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.IOException;
import java.util.List;

public class SatisfactoryImporter {
    private final FileService myFileService;
    private final RepositoryFactory myRepositoryFactory;

    public SatisfactoryImporter(FileService fileService, RepositoryFactory repositoryFactory) {
        myFileService = fileService;
        myRepositoryFactory = repositoryFactory;
    }

    public boolean isAlreadyImported(FormDataMultiPart formParams) {
        SatisfactoryImportProvider provider = new SatisfactoryImportProvider(formParams);
        return myRepositoryFactory.of(ItemDescriptorRepository.class)
                .isAnyRecipeForGameVersion(provider.getGameVersion());
    }

    public boolean isAlreadyImported(String gameVersion) {
        return myRepositoryFactory.of(ItemDescriptorRepository.class)
                .isAnyRecipeForGameVersion(gameVersion);
    }

    public void run(FormDataMultiPart formParams) throws IOException, InterruptedException {
        SatisfactoryImportProvider provider = new SatisfactoryImportProvider(formParams);

        SatisfactoryImport satisfactoryImport = provider.getSatisfactoryImport();

        List<SatisfactoryClassWrapper> classWrapperList = provider.getSatisfactoryClassWrappers(satisfactoryImport);
        Thread imageResourceImporter = new Thread(new ImageResourceImporter(myFileService, satisfactoryImport, provider.getGameVersion()));
        Thread resourceImporter = new Thread(new ResourceImporter(myRepositoryFactory, classWrapperList, provider.getGameVersion()));
        Thread manufacturerImporter = new Thread(new ManufacturerImporter(myRepositoryFactory, classWrapperList, provider.getGameVersion()));
        Thread extractorImporter = new Thread(new ExtractorImporter(myRepositoryFactory, classWrapperList, provider.getGameVersion()));
        Thread itemDescriptorImporter = new Thread(new ItemDescriptorImporter(myRepositoryFactory, classWrapperList, provider.getGameVersion()));

        imageResourceImporter.start();
        resourceImporter.start();
        manufacturerImporter.start();
        extractorImporter.start();
        itemDescriptorImporter.start();

        imageResourceImporter.join();
        resourceImporter.join();
        manufacturerImporter.join();
        extractorImporter.join();
        itemDescriptorImporter.join();
    }
}
