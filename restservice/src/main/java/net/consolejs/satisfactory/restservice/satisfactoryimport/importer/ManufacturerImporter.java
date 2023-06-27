package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.manufacturer.ManufacturerDocument;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.manufacturer.ManufacturerRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;

import java.util.List;
import java.util.Optional;

public class ManufacturerImporter implements Runnable {
    private final RepositoryFactory myRepositoryFactory;
    private final List<SatisfactoryClassWrapper> myClassWrappers;
    private final String myGameVersion;

    public ManufacturerImporter(RepositoryFactory repositoryFactory, List<SatisfactoryClassWrapper> classWrappers,
                                String gameVersion) {
        myRepositoryFactory = repositoryFactory;
        myClassWrappers = classWrappers;
        myGameVersion = gameVersion;
    }

    public void run() {
        ManufacturerRepository repository = myRepositoryFactory.of(ManufacturerRepository.class);
        myClassWrappers
                .stream()
                .filter(entry -> NativeClass.FGBuildableManufacturer.equals(entry.getNativeClass()))
                .findFirst()
                .ifPresent(entry -> {
                    for (SatisfactoryClass clazz : entry.getClasses()) {
                        ManufacturerDocument.Builder builder = ManufacturerDocument
                                .newBuilder()
                                .withGameVersion(myGameVersion)
                                .withClassName(clazz.getClassName())
                                .withDisplayName(clazz.getDisplayName())
                                .withDescription(clazz.getDescription());

                        getDescriptorClazz(clazz).ifPresent(descriptorClazz -> builder
                                .withSmallIcon(getCleanIconValue(descriptorClazz.getSmallIcon()))
                                .withBigIcon(getCleanIconValue(descriptorClazz.getBigIcon())));

                        repository.create(builder.build());
                    }
                });
    }

    private Optional<SatisfactoryClass> getDescriptorClazz(SatisfactoryClass manufacturer) {
        String descriptorClassName = getDescriptorClassName(manufacturer);

        return myClassWrappers
                .stream()
                .filter(entry -> NativeClass.FGBuildingDescriptor.equals(entry.getNativeClass()))
                .flatMap(entry -> entry
                        .getClasses()
                        .stream())
                .filter(entry -> descriptorClassName.equals(entry.getClassName()))
                .findFirst();
    }

    private String getDescriptorClassName(SatisfactoryClass manufacturer) {
        return manufacturer
                .getClassName()
                .replace("Build_", "Desc_");
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
