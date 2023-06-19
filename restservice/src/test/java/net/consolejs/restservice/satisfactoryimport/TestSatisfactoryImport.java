package net.consolejs.restservice.satisfactoryimport;

import net.consolejs.entityview.satisfactory.NativeClass;
import net.consolejs.restservice.satisfactoryimport.model.SatisfactoryClassWrapper;
import net.consolejs.restservice.satisfactoryimport.model.SatisfactoryImport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestSatisfactoryImport {

    @Test
    public void testEqualsSameObject() {
        SatisfactoryImport obj = createSampleObject();
        assertEquals(obj, obj);
    }

    @Test
    public void testEqualsNullObject() {
        SatisfactoryImport obj = createSampleObject();
        assertNotEquals(null, obj);
    }

    @Test
    public void testEqualsDifferentClass() {
        SatisfactoryImport obj = createSampleObject();
        assertNotEquals("Not a SatisfactoryImport object", obj);
    }

    @Test
    public void testEqualsDifferentGameVersion() {
        SatisfactoryImport obj1 = createSampleObject();
        SatisfactoryImport obj2 = createSampleObject().newBuilderFromCurrent()
                .withGameVersion("DifferentGameVersion")
                .build();
        assertNotEquals(obj1, obj2);
    }

    @Test
    public void testEqualsDifferentDocs() {
        SatisfactoryImport obj1 = createSampleObject();
        SatisfactoryImport obj2 = createSampleObject().newBuilderFromCurrent()
                .withDocs(new ArrayList<>())
                .build();
        assertNotEquals(obj1, obj2);
    }

    @Test
    public void testEqualsSameProperties() {
        SatisfactoryImport obj1 = createSampleObject();
        SatisfactoryImport obj2 = createSampleObject();
        assertEquals(obj1, obj2);
    }

    private SatisfactoryImport createSampleObject() {
        List<SatisfactoryClassWrapper> docs = new ArrayList<>();
        docs.add(createSampleWrapper(NativeClass.FGChainsaw));
        docs.add(createSampleWrapper(NativeClass.FGChainsaw));

        return SatisfactoryImport.newBuilder()
                .withGameVersion("GameVersion")
                .withDocs(docs)
                .build();
    }

    private SatisfactoryClassWrapper createSampleWrapper(NativeClass nativeClass) {
        return SatisfactoryClassWrapper.newBuilder()
                .withNativeClass(nativeClass)
                .withClasses(new ArrayList<>())
                .build();
    }
}
