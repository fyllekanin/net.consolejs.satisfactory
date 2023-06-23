package net.consolejs.satisfactory.entityview.document.recipe;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class RecipeProduct {
    @SerializedName("descriptorClassName")
    private final String myDescriptorClassName;
    @SerializedName("amount")
    private final float myAmount;
    @SerializedName("isPart")
    private final boolean myIsPart;

    private RecipeProduct(Builder builder) {
        myDescriptorClassName = builder.myDescriptorClassName;
        myAmount = builder.myAmount;
        myIsPart = builder.myIsPart;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDescriptorClassName() {
        return myDescriptorClassName;
    }

    public float getAmount() {
        return myAmount;
    }

    public boolean isPart() {
        return myIsPart;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RecipeProduct other) {
            return Objects.equals(myDescriptorClassName, other.myDescriptorClassName) &&
                    Objects.equals(myAmount, other.myAmount) &&
                    Objects.equals(myIsPart, other.myIsPart);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myDescriptorClassName, myAmount);
    }

    public static class Builder {
        private String myDescriptorClassName;
        private float myAmount;
        private boolean myIsPart;

        private Builder() {
        }

        public Builder withDescriptorClassName(String descriptorClassName) {
            myDescriptorClassName = descriptorClassName;
            return this;
        }

        public Builder withAmount(float amount) {
            myAmount = amount;
            return this;
        }

        public Builder isPart(boolean isPart) {
            myIsPart = isPart;
            return this;
        }

        public RecipeProduct build() {
            return new RecipeProduct(this);
        }
    }
}
