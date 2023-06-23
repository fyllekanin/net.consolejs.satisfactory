package net.consolejs.satisfactory.entityview.document.recipe;

import com.google.gson.annotations.SerializedName;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;

public class RecipeIngredient {
    @SerializedName("className")
    private final String myClassName;
    @SerializedName("descriptorClassName")
    private final String myDescriptorClassName;
    @SerializedName("amount")
    private final int myAmount;

    @SerializedName("isPart")
    private final boolean myIsPart;
    @SerializedName("resourceType")
    private final ResourceType myResourceType;

    private RecipeIngredient(Builder builder) {
        myClassName = builder.myClassName;
        myDescriptorClassName = builder.myDescriptorClassName;
        myAmount = builder.myAmount;
        myIsPart = builder.myIsPart;
        myResourceType = builder.myResourceType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getClassName() {
        return myClassName;
    }

    public String getDescriptorClassName() {
        return myDescriptorClassName;
    }

    public int getAmount() {
        return myAmount;
    }

    public boolean isPart() {
        return myIsPart;
    }

    public ResourceType getResourceType() {
        return myResourceType;
    }

    public static class Builder {
        private String myClassName;
        private String myDescriptorClassName;
        private int myAmount;
        private boolean myIsPart;
        private ResourceType myResourceType;


        private Builder() {
            // Empty
        }

        public Builder withClassName(String className) {
            myClassName = className;
            return this;
        }

        public Builder withDescriptorClassName(String descriptorClassName) {
            myDescriptorClassName = descriptorClassName;
            return this;
        }

        public Builder withAmount(int amount) {
            myAmount = amount;
            return this;
        }

        public Builder isPart(boolean isPart) {
            myIsPart = isPart;
            return this;
        }

        public Builder withResourceType(ResourceType resourceType) {
            myResourceType = resourceType;
            return this;
        }

        public RecipeIngredient build() {
            return new RecipeIngredient(this);
        }
    }
}
