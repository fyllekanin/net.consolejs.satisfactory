package net.consolejs.satisfactory.restservice.satisfactoryimport.model;


import org.glassfish.grizzly.utils.Pair;

import java.util.List;
import java.util.zip.ZipEntry;

public class SatisfactoryImport {
    private final Pair<ZipEntry, byte[]> myDocs;
    private final List<Pair<ZipEntry, byte[]>> myImageResources;

    private SatisfactoryImport(Builder builder) {
        myDocs = builder.myDocs;
        myImageResources = builder.myImageResources;
    }

    public Pair<ZipEntry, byte[]> getDocs() {
        return myDocs;
    }

    public List<Pair<ZipEntry, byte[]>> getImageResources() {
        return myImageResources;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Pair<ZipEntry, byte[]> myDocs;
        private List<Pair<ZipEntry, byte[]>> myImageResources;

        private Builder() {
            // Empty
        }

        public Builder withDocs(Pair<ZipEntry, byte[]> docs) {
            myDocs = docs;
            return this;
        }

        public Builder withImageResources(List<Pair<ZipEntry, byte[]>> imageResources) {
            myImageResources = imageResources;
            return this;
        }

        public SatisfactoryImport build() {
            return new SatisfactoryImport(this);
        }
    }
}
