package net.consolejs.satisfactory.entityview.document.recipe;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class RecipeDocument {
    @SerializedName("gameVersion")
    private final String myGameVersion;
    @SerializedName("className")
    private final String myClassName;
    @SerializedName("fullName")
    private final String myFullName;
    @SerializedName("displayName")
    private final String myDisplayName;
    @SerializedName("ingredients")
    private final List<RecipeIngredient> myIngredients;
    @SerializedName("duration")
    private final float myDuration;
    @SerializedName("producedIn")
    private final String myProducedIn;
    @SerializedName("isAlternate")
    private final boolean myIsAlternate;
    @SerializedName("products")
    private final List<RecipeProduct> myProducts;

    private RecipeDocument(Builder builder) {
        myGameVersion = builder.myGameVersion;
        myClassName = builder.myClassName;
        myFullName = builder.myFullName;
        myDisplayName = builder.myDisplayName;
        myIngredients = builder.myIngredients;
        myDuration = builder.myDuration;
        myProducedIn = builder.myProducedIn;
        myIsAlternate = builder.myIsAlternate;
        myProducts = builder.myProducts;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getGameVersion() {
        return myGameVersion;
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

    public float getDuration() {
        return myDuration;
    }

    public List<RecipeIngredient> getIngredients() {
        return myIngredients;
    }

    public String getProducedIn() {
        return myProducedIn;
    }

    public boolean isAlternate() {
        return myIsAlternate;
    }

    public List<RecipeProduct> getProducts() {
        return myProducts;
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
        return Objects.equals(myGameVersion, other.myGameVersion) &&
                Objects.equals(myClassName, other.myClassName) &&
                Objects.equals(myFullName, other.myFullName) &&
                Objects.equals(myDisplayName, other.myDisplayName) &&
                Objects.equals(myIngredients, other.myIngredients) &&
                Objects.equals(myDuration, other.myDuration) &&
                Objects.equals(myProducedIn, other.myProducedIn) &&
                Objects.equals(myIsAlternate, other.myIsAlternate) &&
                Objects.equals(myProducts, other.myProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                myGameVersion,
                myClassName,
                myFullName,
                myDisplayName,
                myIngredients,
                myDuration,
                myProducedIn,
                myIsAlternate,
                myProducts
        );
    }

    public static class Builder {
        private String myGameVersion;
        private String myClassName;
        private String myFullName;
        private String myDisplayName;
        private List<RecipeIngredient> myIngredients;
        private float myDuration;
        private String myProducedIn;
        private boolean myIsAlternate;
        private List<RecipeProduct> myProducts;

        private Builder() {
            // Empty
        }

        public Builder withGameVersion(String gameVersion) {
            myGameVersion = gameVersion;
            return this;
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

        public Builder withIngredients(List<RecipeIngredient> ingredients) {
            myIngredients = ingredients;
            return this;
        }

        public Builder withDuration(float duration) {
            myDuration = duration;
            return this;
        }

        public Builder withProducedIn(String producedIn) {
            myProducedIn = producedIn;
            return this;
        }

        public Builder isAlternate(boolean isAlternate) {
            myIsAlternate = isAlternate;
            return this;
        }

        public Builder withProducts(List<RecipeProduct> products) {
            myProducts = products;
            return this;
        }

        public RecipeDocument build() {
            return new RecipeDocument(this);
        }
    }
}
