package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class SatisfactoryImport {
    @SerializedName("gameVersion")
    private final String myGameVersion;
    @SerializedName("docs")
    private final List<SatisfactoryClassWrapper> myDocs;

    private SatisfactoryImport(Builder builder) {
        myGameVersion = builder.myGameVersion;
        myDocs = builder.myDocs;
    }

    public String getGameVersion() {
        return myGameVersion;
    }

    public List<SatisfactoryClassWrapper> getDocs() {
        return myDocs;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder newBuilderFromCurrent() {
        return new Builder(this);
    }

    public static class Builder {
        private String myGameVersion;
        private List<SatisfactoryClassWrapper> myDocs;

        private Builder() {
            // Empty
        }

        private Builder(SatisfactoryImport original) {
            myGameVersion = original.myGameVersion;
            myDocs = original.myDocs;
        }

        public Builder withGameVersion(String gameVersion) {
            myGameVersion = gameVersion;
            return this;
        }

        public Builder withDocs(List<SatisfactoryClassWrapper> docs) {
            myDocs = docs;
            return this;
        }

        public SatisfactoryImport build() {
            return new SatisfactoryImport(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SatisfactoryImport other = (SatisfactoryImport) obj;

        return Objects.equals(getGameVersion(), other.getGameVersion()) &&
                Objects.equals(getDocs(), other.getDocs());
    }
}
