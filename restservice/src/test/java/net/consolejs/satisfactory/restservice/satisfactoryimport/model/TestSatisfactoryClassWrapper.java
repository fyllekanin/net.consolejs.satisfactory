package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import net.consolejs.satisfactory.entityview.satisfactory.NativeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestSatisfactoryClassWrapper {

    @Test
    public void testEqualsSameObject() {
        // Given
        SatisfactoryClassWrapper obj = createSampleObject();

        // When Then
        assertEquals(obj, obj);
    }

    @Test
    public void testEqualsNullObject() {
        // Given
        SatisfactoryClassWrapper obj = createSampleObject();

        // When Then
        assertNotEquals(null, obj);
    }

    @Test
    public void testEqualsDifferentClass() {
        // Given
        SatisfactoryClassWrapper obj = createSampleObject();

        // When Then
        assertNotEquals("Not a SatisfactoryClassWrapper object", obj);
    }

    @Test
    public void testEqualsDifferentNativeClass() {
        // Given
        SatisfactoryClassWrapper obj1 = createSampleObject();
        SatisfactoryClassWrapper obj2 = createSampleObject().newBuilderFromCurrent()
                .withNativeClass(NativeClass.FGChainsaw)
                .build();

        // When Then
        assertNotEquals(obj1, obj2);
    }

    @Test
    public void testEqualsDifferentClasses() {
        // Given
        SatisfactoryClassWrapper obj1 = createSampleObject();
        SatisfactoryClassWrapper obj2 = createSampleObject().newBuilderFromCurrent()
                .withClasses(new ArrayList<>())
                .build();

        // When Then
        assertNotEquals(obj1, obj2);
    }

    @Test
    public void testEqualsSameProperties() {
        // Given
        SatisfactoryClassWrapper obj1 = createSampleObject();
        SatisfactoryClassWrapper obj2 = createSampleObject();

        // When Then
        assertEquals(obj1, obj2);
    }

    private SatisfactoryClassWrapper createSampleObject() {
        List<SatisfactoryClass> classes = new ArrayList<>();
        classes.add(SatisfactoryClass.newBuilder().withClassName("Class1").build());
        classes.add(SatisfactoryClass.newBuilder().withClassName("Class2").build());

        return SatisfactoryClassWrapper.newBuilder()
                .withNativeClass(NativeClass.FGBuildable)
                .withClasses(classes)
                .build();
    }

}
