package net.consolejs.satisfactory.entityview.document.recipe;

import com.google.gson.annotations.SerializedName;

public class RecipeIngredient {
    @SerializedName("ClassName")
    private final String myClassName;

    @SerializedName("Amount")
    private final int myAmount;

    private RecipeIngredient(Builder builder) {
        myClassName = builder.myClassName;
        myAmount = builder.myAmount;
    }

    public String getClassName() {
        return myClassName;
    }

    public int getAmount() {
        return myAmount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String myClassName;

        private int myAmount;


        private Builder() {
            // Empty
        }

        public Builder withClassName(String className) {
            myClassName = className;
            return this;
        }

        public Builder withAmount(int amount) {
            myAmount = amount;
            return this;
        }

        public RecipeIngredient build() {
            return new RecipeIngredient(this);
        }
    }
}
