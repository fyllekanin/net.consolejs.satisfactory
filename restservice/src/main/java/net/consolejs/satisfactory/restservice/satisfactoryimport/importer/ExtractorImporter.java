package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.extractor.ExtractorDocument;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.extractor.ExtractorRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractorImporter implements Runnable {
    private static final Pattern ALLOWED_RESOURCES_PATTERN = Pattern.compile("[a-zA-Z_]+.([a-z-A-Z_]+_C)");
    private static final String RF_SOLID_RESOURCE_FORM = "(RF_SOLID)";
    private final RepositoryFactory myRepositoryFactory;
    private final List<SatisfactoryClassWrapper> myClassWrappers;
    private final String myGameVersion;

    public ExtractorImporter(RepositoryFactory repositoryFactory, List<SatisfactoryClassWrapper> classWrappers,
                             String gameVersion) {
        myRepositoryFactory = repositoryFactory;
        myClassWrappers = classWrappers;
        myGameVersion = gameVersion;
    }

    public void run() {
        ExtractorRepository repository = myRepositoryFactory.of(ExtractorRepository.class);
        myClassWrappers
                .stream()
                .filter(entry -> NativeClass.FGBuildableResourceExtractor.equals(entry.getNativeClass()) ||
                        NativeClass.FGBuildableWaterPump.equals(entry.getNativeClass()))
                .forEach(entry -> {
                    for (SatisfactoryClass clazz : entry.getClasses()) {
                        ExtractorDocument.Builder builder = ExtractorDocument
                                .newBuilder()
                                .withGameVersion(myGameVersion)
                                .withClassName(clazz.getClassName())
                                .withResourceType(getResourceType(clazz.getAllowedResourceForms()))
                                .withDisplayName(clazz.getDisplayName())
                                .withDescription(clazz.getDescription())
                                .withAllowedResources(getAllowedResources(clazz));

                        getDescriptorClazz(clazz).ifPresent(descriptorClazz -> builder
                                .withSmallIcon(getCleanIconValue(descriptorClazz.getSmallIcon()))
                                .withBigIcon(getCleanIconValue(descriptorClazz.getBigIcon())));

                        repository.create(builder.build());
                    }
                });
    }

    private List<String> getAllowedResources(SatisfactoryClass satisfactoryClass) {
        if (satisfactoryClass.getAllowedResources() == null) {
            return Collections.emptyList();
        }
        Matcher matcher = ALLOWED_RESOURCES_PATTERN.matcher(satisfactoryClass.getAllowedResources());
        List<String> allowedResources = new ArrayList<>();
        while (matcher.find()) {
            allowedResources.add(matcher.group(1));
        }
        return allowedResources;
    }

    private ResourceType getResourceType(String allowedResourceForms) {
        return RF_SOLID_RESOURCE_FORM.equals(allowedResourceForms) ? ResourceType.RF_SOLID : ResourceType.RF_LIQUID;
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
