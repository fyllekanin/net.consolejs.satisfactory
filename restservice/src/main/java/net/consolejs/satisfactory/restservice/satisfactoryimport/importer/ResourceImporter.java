package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.resource.ResourceDocument;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.resource.ResourceRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;

import java.util.List;

public class ResourceImporter implements Runnable {
    private final RepositoryFactory myRepositoryFactory;
    private final List<SatisfactoryClassWrapper> myClassWrappers;
    private final String myGameVersion;

    public ResourceImporter(RepositoryFactory repositoryFactory, List<SatisfactoryClassWrapper> classWrappers, String gameVersion) {
        myRepositoryFactory = repositoryFactory;
        myClassWrappers = classWrappers;
        myGameVersion = gameVersion;
    }

    public void run() {
        ResourceRepository repository = myRepositoryFactory.of(ResourceRepository.class);
        myClassWrappers.stream()
                .filter(entry -> NativeClass.FGResourceDescriptor.equals(entry.getNativeClass()))
                .findFirst()
                .ifPresent(entry -> {
                    for (SatisfactoryClass clazz : entry.getClasses()) {
                        repository.create(ResourceDocument.newBuilder()
                                .withGameVersion(myGameVersion)
                                .withClassName(clazz.getClassName())
                                .withDisplayName(clazz.getDisplayName())
                                .withDescription(clazz.getDescription())
                                .withSmallIcon(getCleanIconValue(clazz.getSmallIcon()))
                                .withBigIcon(getCleanIconValue(clazz.getBigIcon()))
                                .build());
                    }
                });
    }

    private String getCleanIconValue(String icon) {
        return icon == null ? null : icon.replace("Texture2D ", "");
    }
}
