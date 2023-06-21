package net.consolejs.satisfactory.restservice.satisfactoryimport.importer;

import net.consolejs.satisfactory.entityview.document.resource.ResourceDocument;
import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.resource.ResourceRepository;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.satisfactory.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestResourceImporter {
    @Mock
    private RepositoryFactory repositoryFactory;
    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private SatisfactoryClassWrapper classWrapper;
    @Mock
    private SatisfactoryClass resourceClass;
    @Mock
    private SatisfactoryClass anotherResourceClass;

    @BeforeEach
    public void setup() {
        when(repositoryFactory.of(ResourceRepository.class)).thenReturn(resourceRepository);

    }

    @Test
    public void testImportResourcesNoClassWrapper() {
        // Given
        List<SatisfactoryClassWrapper> classWrappers = Collections.emptyList();
        String gameVersion = "1.0";

        // When
        new ResourceImporter(repositoryFactory, classWrappers, gameVersion).run();

        // Then
        verifyNoInteractions(resourceRepository);
    }

    @Test
    public void testImportResourcesNoMatchingClassWrapper() {
        // Given
        SatisfactoryClassWrapper nonResourceClassWrapper = mock(SatisfactoryClassWrapper.class);
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(nonResourceClassWrapper);
        String gameVersion = "1.0";

        // When
        new ResourceImporter(repositoryFactory, classWrappers, gameVersion).run();

        // Then
        verifyNoInteractions(resourceRepository);
    }

    @Test
    public void testImportResourcesOneMatchingClassWrapperNoClasses() {
        // Given
        when(classWrapper.getNativeClass()).thenReturn(NativeClass.FGResourceDescriptor);
        when(classWrapper.getClasses()).thenReturn(Collections.emptyList());
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(classWrapper);
        String gameVersion = "1.0";

        // When
        new ResourceImporter(repositoryFactory, classWrappers, gameVersion).run();

        // Then
        verifyNoInteractions(resourceRepository);
    }

    @Test
    public void testImportResourcesOneMatchingClassWrapperOneClass() {
        // Given
        when(classWrapper.getNativeClass()).thenReturn(NativeClass.FGResourceDescriptor);
        List<SatisfactoryClass> classes = Collections.singletonList(resourceClass);
        when(classWrapper.getClasses()).thenReturn(classes);
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(classWrapper);
        String gameVersion = "1.0";

        // When
        new ResourceImporter(repositoryFactory, classWrappers, gameVersion).run();

        // Then
        verify(resourceClass).getClassName();
        verify(resourceClass).getDisplayName();
        verify(resourceClass).getDescription();
        verify(resourceClass).getSmallIcon();
        verify(resourceClass).getBigIcon();
        verify(resourceRepository).create(any(ResourceDocument.class));
    }

    @Test
    public void testImportResourcesOneMatchingClassWrapperMultipleClasses() {
        // Given
        when(classWrapper.getNativeClass()).thenReturn(NativeClass.FGResourceDescriptor);
        List<SatisfactoryClass> classes = Arrays.asList(resourceClass, anotherResourceClass);
        when(classWrapper.getClasses()).thenReturn(classes);
        List<SatisfactoryClassWrapper> classWrappers = Collections.singletonList(classWrapper);
        String gameVersion = "1.0";

        // When
        new ResourceImporter(repositoryFactory, classWrappers, gameVersion).run();

        // Then
        verify(resourceClass).getClassName();
        verify(resourceClass).getDisplayName();
        verify(resourceClass).getDescription();
        verify(resourceClass).getSmallIcon();
        verify(resourceClass).getBigIcon();
        verify(anotherResourceClass).getClassName();
        verify(anotherResourceClass).getDisplayName();
        verify(anotherResourceClass).getDescription();
        verify(anotherResourceClass).getSmallIcon();
        verify(anotherResourceClass).getBigIcon();
        verify(resourceRepository, times(2)).create(any(ResourceDocument.class));
    }
}
