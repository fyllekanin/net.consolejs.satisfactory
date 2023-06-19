package net.consolejs.restservice.satisfactoryimport;

import net.consolejs.entityview.satisfactory.NativeClass;
import net.consolejs.restservice.satisfactoryimport.model.SatisfactoryClass;
import net.consolejs.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestSatisfactoryClassWrapper {

    @Test
    public void testEqualsSameObject() {
        SatisfactoryClassWrapper obj = createSampleObject();
        assertEquals(obj, obj);
    }

    @Test
    public void testEqualsNullObject() {
        SatisfactoryClassWrapper obj = createSampleObject();
        assertNotEquals(null, obj);
    }

    @Test
    public void testEqualsDifferentClass() {
        SatisfactoryClassWrapper obj = createSampleObject();
        assertNotEquals("Not a SatisfactoryClassWrapper object", obj);
    }

    @Test
    public void testEqualsDifferentNativeClass() {
        SatisfactoryClassWrapper obj1 = createSampleObject();
        SatisfactoryClassWrapper obj2 = createSampleObject().newBuilderFromCurrent()
                .withNativeClass(NativeClass.FGChainsaw)
                .build();
        assertNotEquals(obj1, obj2);
    }

    @Test
    public void testEqualsDifferentClasses() {
        SatisfactoryClassWrapper obj1 = createSampleObject();
        SatisfactoryClassWrapper obj2 = createSampleObject().newBuilderFromCurrent()
                .withClasses(new ArrayList<>())
                .build();
        assertNotEquals(obj1, obj2);
    }

    @Test
    public void testEqualsSameProperties() {
        SatisfactoryClassWrapper obj1 = createSampleObject();
        SatisfactoryClassWrapper obj2 = createSampleObject();
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
