package net.consolejs.satisfactory.entityview.document.recipe;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class RecipeDocument {
    @SerializedName("ClassName")
    private final String myClassName;

    @SerializedName("FullName")
    private final String myFullName;

    @SerializedName("mDisplayName")
    private final String myDisplayName;

    private RecipeDocument(Builder builder) {
        myClassName = builder.myClassName;
        myFullName = builder.myFullName;
        myDisplayName = builder.myDisplayName;
    }

    public String getClassName() {
        return myClassName;
    }

    public String getFullName() {
        return myFullName;
    }

    public String getDisplayName() {
        return myDisplayName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String myClassName;
        private String myFullName;
        private String myDisplayName;

        private Builder() {
            // Empty
        }

        public Builder withClassName(String className) {
            myClassName = className;
            return this;
        }

        public Builder withFullName(String fullName) {
            myFullName = fullName;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            myDisplayName = displayName;
            return this;
        }

        public RecipeDocument build() {
            return new RecipeDocument(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecipeDocument other = (RecipeDocument) o;
        return Objects.equals(myClassName, other.myClassName) &&
                Objects.equals(myFullName, other.myFullName) &&
                Objects.equals(myDisplayName, other.myDisplayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myClassName, myFullName, myDisplayName);
    }
}
