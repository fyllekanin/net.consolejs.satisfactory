package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SatisfactoryClassUnlocks {
    @SerializedName("Class")
    private final String myClassName;
    @SerializedName("mRecipes")
    private final String myRecipes;

    private SatisfactoryClassUnlocks(Builder builder) {
        myClassName = builder.myClassName;
        myRecipes = builder.myRecipes;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getClassName() {
        return myClassName;
    }

    public String getRecipes() {
        return myRecipes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SatisfactoryClassUnlocks other = (SatisfactoryClassUnlocks) obj;

        return Objects.equals(getClassName(), other.getClassName()) &&
                Objects.equals(getRecipes(), other.getRecipes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                myClassName,
                myRecipes
        );
    }

    public static class Builder {
        private String myClassName;
        private String myRecipes;

        private Builder() {
            // Empty
        }

        public Builder withClassName(String className) {
            myClassName = className;
            return this;
        }

        public Builder withRecipes(String recipes) {
            myRecipes = recipes;
            return this;
        }

        public SatisfactoryClassUnlocks build() {
            return new SatisfactoryClassUnlocks(this);
        }
    }
}
