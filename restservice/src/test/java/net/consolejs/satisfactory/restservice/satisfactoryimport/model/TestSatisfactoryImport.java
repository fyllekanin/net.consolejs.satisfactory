package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import org.glassfish.grizzly.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

public class TestSatisfactoryImport {
    @Test
    public void testConstructorAndGetters() {
        // Given
        Pair<ZipEntry, byte[]> docs = new Pair<>(new ZipEntry("doc.txt"), new byte[]{1, 2, 3});
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();
        imageResources.add(new Pair<>(new ZipEntry("image1.jpg"), new byte[]{4, 5, 6}));
        imageResources.add(new Pair<>(new ZipEntry("image2.jpg"), new byte[]{7, 8, 9}));

        // When
        SatisfactoryImport importObj = SatisfactoryImport.newBuilder()
                .withDocs(docs)
                .withImageResources(imageResources)
                .build();

        // Then
        Assertions.assertEquals(docs, importObj.getDocs());
        Assertions.assertEquals(imageResources, importObj.getImageResources());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        Pair<ZipEntry, byte[]> docs = new Pair<>(new ZipEntry("doc.txt"), new byte[]{1, 2, 3});
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();
        imageResources.add(new Pair<>(new ZipEntry("image1.jpg"), new byte[]{4, 5, 6}));
        imageResources.add(new Pair<>(new ZipEntry("image2.jpg"), new byte[]{7, 8, 9}));

        // When
        SatisfactoryImport import1 = SatisfactoryImport.newBuilder()
                .withDocs(docs)
                .withImageResources(imageResources)
                .build();

        SatisfactoryImport import2 = SatisfactoryImport.newBuilder()
                .withDocs(docs)
                .withImageResources(imageResources)
                .build();

        // Then
        Assertions.assertEquals(import1, import2);
        Assertions.assertEquals(import1.hashCode(), import2.hashCode());
    }

    @Test
    public void testEqualsThisEqualsObj() {
        // Given
        Pair<ZipEntry, byte[]> docs = new Pair<>(new ZipEntry("doc.txt"), new byte[]{1, 2, 3});
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();
        imageResources.add(new Pair<>(new ZipEntry("image1.jpg"), new byte[]{4, 5, 6}));
        imageResources.add(new Pair<>(new ZipEntry("image2.jpg"), new byte[]{7, 8, 9}));

        SatisfactoryImport importObj = SatisfactoryImport.newBuilder()
                .withDocs(docs)
                .withImageResources(imageResources)
                .build();

        // When
        boolean result = importObj.equals(importObj);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void testEqualsObjIsNull() {
        // Given
        Pair<ZipEntry, byte[]> docs = new Pair<>(new ZipEntry("doc.txt"), new byte[]{1, 2, 3});
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();
        imageResources.add(new Pair<>(new ZipEntry("image1.jpg"), new byte[]{4, 5, 6}));
        imageResources.add(new Pair<>(new ZipEntry("image2.jpg"), new byte[]{7, 8, 9}));

        SatisfactoryImport importObj = SatisfactoryImport.newBuilder()
                .withDocs(docs)
                .withImageResources(imageResources)
                .build();

        // When
        boolean result = importObj.equals(null);

        // Then
        Assertions.assertFalse(result);
    }

    @Test
    public void testBuilderWithDocs() {
        // Given
        Pair<ZipEntry, byte[]> docs = new Pair<>(new ZipEntry("doc.txt"), new byte[]{1, 2, 3});

        // When
        SatisfactoryImport importObj = SatisfactoryImport.newBuilder()
                .withDocs(docs)
                .build();

        // Then
        Assertions.assertEquals(docs, importObj.getDocs());
        Assertions.assertNull(importObj.getImageResources());
    }

    @Test
    public void testBuilderWithImageResources() {
        // Given
        List<Pair<ZipEntry, byte[]>> imageResources = new ArrayList<>();
        imageResources.add(new Pair<>(new ZipEntry("image1.jpg"), new byte[]{4, 5, 6}));
        imageResources.add(new Pair<>(new ZipEntry("image2.jpg"), new byte[]{7, 8, 9}));

        // When
        SatisfactoryImport importObj = SatisfactoryImport.newBuilder()
                .withImageResources(imageResources)
                .build();

        // Then
        Assertions.assertNull(importObj.getDocs());
        Assertions.assertEquals(imageResources, importObj.getImageResources());
    }
}
