package net.consolejs.satisfactory.entityview.document.gameimport;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class GameImportDocument {
    @SerializedName("type")
    private final GameImportType myType;
    @SerializedName("gameVersion")
    private final String myGameVersion;
    @SerializedName("importedAt")
    private final long myImportedAt;

    private GameImportDocument(Builder builder) {
        myType = builder.myType;
        myGameVersion = builder.myGameVersion;
        myImportedAt = builder.myImportedAt;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public GameImportType getType() {
        return myType;
    }

    public String getGameVersion() {
        return myGameVersion;
    }

    public long getImportedAt() {
        return myImportedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameImportDocument other = (GameImportDocument) obj;
        return Objects.equals(myGameVersion, other.myGameVersion) &&
                Objects.equals(myType, other.myType) &&
                Objects.equals(myImportedAt, other.myImportedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myGameVersion, myType, myImportedAt);
    }

    public static class Builder {
        private GameImportType myType;
        private String myGameVersion;
        private long myImportedAt;

        private Builder() {
            // Empty
        }

        public Builder withType(GameImportType type) {
            myType = type;
            return this;
        }

        public Builder withGameVersion(String gameVersion) {
            myGameVersion = gameVersion;
            return this;
        }

        public Builder withImportedAt(long importedAt) {
            myImportedAt = importedAt;
            return this;
        }

        public GameImportDocument build() {
            return new GameImportDocument(this);
        }
    }
}
