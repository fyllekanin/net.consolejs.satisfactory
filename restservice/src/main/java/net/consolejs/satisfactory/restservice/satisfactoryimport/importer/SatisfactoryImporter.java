package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.gameimport.GameImportDocument;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.gameimport.GameImportRepository;
import net.consolejs.satisfactory.repository.itemdescriptor.ItemDescriptorRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryImport;
import net.consolejs.satisfactory.restservice.satisfactoryimport.provider.SatisfactoryImportProvider;
import net.consolejs.satisfactory.service.file.FileService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
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
        return myRepositoryFactory
                .of(GameImportRepository.class)
                .isImportAlreadyPresent(provider.getGameVersion());
    }

    public boolean isAlreadyImported(String gameVersion) {
        return myRepositoryFactory
                .of(ItemDescriptorRepository.class)
                .isAnyRecipeForGameVersion(gameVersion);
    }

    public void run(FormDataMultiPart formParams) throws IOException, InterruptedException {
        SatisfactoryImportProvider provider = new SatisfactoryImportProvider(formParams);
        List<Thread> importers = getImporters(provider);

        importers.forEach(Thread::start);
        for (Thread importer : importers) {
            importer.join();
        }

        myRepositoryFactory.of(GameImportRepository.class)
                           .create(GameImportDocument.newBuilder()
                                                     .withType(provider.getType())
                                                     .withGameVersion(provider.getGameVersion())
                                                     .withImportedAt(Instant.now()
                                                                            .toEpochMilli())
                                                     .build());
    }

    private List<Thread> getImporters(SatisfactoryImportProvider provider) throws IOException {
        SatisfactoryImport satisfactoryImport = provider.getSatisfactoryImport();
        List<SatisfactoryClassWrapper> classWrapperList = provider.getSatisfactoryClassWrappers(satisfactoryImport);

        return Arrays.asList(
                new Thread(new ImageResourceImporter(myFileService, satisfactoryImport,
                                                     provider.getGameVersion())),
                new Thread(new ResourceImporter(myRepositoryFactory, classWrapperList,
                                                provider.getGameVersion())),
                new Thread(new ManufacturerImporter(myRepositoryFactory, classWrapperList,
                                                    provider.getGameVersion())),
                new Thread(new ExtractorImporter(myRepositoryFactory, classWrapperList,
                                                 provider.getGameVersion())),
                new Thread(new ItemDescriptorImporter(myRepositoryFactory, classWrapperList,
                                                      provider.getGameVersion()))
        );
    }
}
