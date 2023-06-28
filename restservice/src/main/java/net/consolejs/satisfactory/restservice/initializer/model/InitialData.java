package net.consolejs.satisfactory.restservice.initializer.model;

import com.google.gson.annotations.SerializedName;
import net.consolejs.satisfactory.entityview.document.gameimport.GameImportDocument;

import java.util.List;
import java.util.Objects;

public class InitialData {
    @SerializedName("gameVersions")
    private final List<GameImportDocument> myGameVersions;

    private InitialData(Builder builder) {
        myGameVersions = builder.myGameVersions;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<GameImportDocument> getGameVersions() {
        return myGameVersions;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InitialData other = (InitialData) obj;
        return Objects.equals(myGameVersions, other.myGameVersions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(myGameVersions);
    }

    public static class Builder {
        private List<GameImportDocument> myGameVersions;

        private Builder() {
            // Empty
        }

        public Builder withGameVersions(List<GameImportDocument> gameVersions) {
            myGameVersions = gameVersions;
            return this;
        }

        public InitialData build() {
            return new InitialData(this);
        }
    }
}
