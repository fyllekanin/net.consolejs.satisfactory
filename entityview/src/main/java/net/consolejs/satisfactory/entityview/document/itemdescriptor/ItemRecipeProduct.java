package net.consolejs.satisfactory.entityview.document.itemdescriptor;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ItemRecipeProduct {
    @SerializedName("itemClassName")
    private final String myItemClassName;
    @SerializedName("amount")
    private final float myAmount;
    @SerializedName("isResource")
    private final boolean myIsResource;
    @SerializedName("amountPerMinute")
    private final float myAmountPerMinute;

    private ItemRecipeProduct(Builder builder) {
        myItemClassName = builder.myItemClassName;
        myAmount = builder.myAmount;
        myIsResource = builder.myIsResource;
        myAmountPerMinute = builder.myAmountPerMinute;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getItemClassName() {
        return myItemClassName;
    }

    public float getAmount() {
        return myAmount;
    }

    public boolean isResource() {
        return myIsResource;
    }

    public float getAmountPerMinute() {
        return myAmountPerMinute;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ItemRecipeProduct other = (ItemRecipeProduct) obj;
        return Objects.equals(myItemClassName, other.myItemClassName) &&
                Float.compare(myAmount, other.myAmount) == 0 &&
                Objects.equals(myIsResource, other.myIsResource) &&
                Objects.equals(myAmountPerMinute, other.myAmountPerMinute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myItemClassName, myAmount, myIsResource, myAmountPerMinute);
    }

    public static class Builder {
        private String myItemClassName;
        private float myAmount;
        private boolean myIsResource;
        private float myAmountPerMinute;

        private Builder() {
        }

        public Builder withItemClassName(String itemClassName) {
            myItemClassName = itemClassName;
            return this;
        }

        public Builder withAmount(float amount) {
            myAmount = amount;
            return this;
        }

        public Builder isResource(boolean isResource) {
            myIsResource = isResource;
            return this;
        }

        public Builder withAmountPerMinute(float amountPerMinute) {
            myAmountPerMinute = amountPerMinute;
            return this;
        }

        public ItemRecipeProduct build() {
            return new ItemRecipeProduct(this);
        }
    }
}
