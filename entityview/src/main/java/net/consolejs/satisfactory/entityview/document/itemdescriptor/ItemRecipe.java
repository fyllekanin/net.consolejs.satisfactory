package net.consolejs.satisfactory.entityview.document.itemdescriptor;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class ItemRecipe {
    @SerializedName("className")
    private final String myClassName;
    @SerializedName("displayName")
    private final String myDisplayName;
    @SerializedName("ingredients")
    private final List<ItemRecipeIngredient> myIngredients;
    @SerializedName("produces")
    private final List<ItemRecipeProduct> myProduces;
    @SerializedName("isAlternate")
    private final boolean myIsAlternate;
    @SerializedName("manufacturerClassName")
    private final String myManufacturerClassName;

    private ItemRecipe(Builder builder) {
        myClassName = builder.myClassName;
        myDisplayName = builder.myDisplayName;
        myIngredients = builder.myIngredients;
        myProduces = builder.myProduces;
        myIsAlternate = builder.myIsAlternate;
        myManufacturerClassName = builder.myManufacturerClassName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getClassName() {
        return myClassName;
    }

    public String getDisplayName() {
        return myDisplayName;
    }

    public List<ItemRecipeIngredient> getIngredients() {
        return myIngredients;
    }

    public List<ItemRecipeProduct> getProduces() {
        return myProduces;
    }

    public int getProducesSize() {
        return myProduces.size();
    }

    public boolean isAlternate() {
        return myIsAlternate;
    }

    public String getManufacturerClassName() {
        return myManufacturerClassName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ItemRecipe other = (ItemRecipe) obj;
        return Objects.equals(myClassName, other.myClassName) &&
                Objects.equals(myDisplayName, other.myDisplayName) &&
                Objects.equals(myIngredients, other.myIngredients) &&
                Objects.equals(myProduces, other.myProduces) &&
                Objects.equals(myIsAlternate, other.myIsAlternate) &&
                Objects.equals(myManufacturerClassName, other.myManufacturerClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myClassName, myDisplayName, myIngredients, myProduces, myIsAlternate,
                            myManufacturerClassName);
    }

    public static class Builder {
        private String myClassName;
        private String myDisplayName;
        private List<ItemRecipeIngredient> myIngredients;
        private List<ItemRecipeProduct> myProduces;
        private boolean myIsAlternate;
        private String myManufacturerClassName;

        private Builder() {
        }

        public Builder withClassName(String className) {
            myClassName = className;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            myDisplayName = displayName;
            return this;
        }

        public Builder withIngredients(List<ItemRecipeIngredient> ingredients) {
            myIngredients = ingredients;
            return this;
        }

        public Builder withProduces(List<ItemRecipeProduct> produces) {
            myProduces = produces;
            return this;
        }

        public Builder isAlternate(boolean isAlternate) {
            myIsAlternate = isAlternate;
            return this;
        }

        public Builder withManufacturerClassName(String manufacturerClassName) {
            myManufacturerClassName = manufacturerClassName;
            return this;
        }

        public ItemRecipe build() {
            return new ItemRecipe(this);
        }
    }
}
