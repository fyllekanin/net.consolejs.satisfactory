package net.consolejs.satisfactory.restservice.planner.model;

import com.google.gson.annotations.SerializedName;
import net.consolejs.satisfactory.entityview.document.itemdescriptor.ItemRecipe;

import java.util.List;

public class PlannerItem {
    @SerializedName("className")
    private final String myClassName;
    @SerializedName("displayName")
    private final String myDisplayName;
    @SerializedName("recipes")
    private final List<ItemRecipe> myRecipes;

    private PlannerItem(Builder builder) {
        myClassName = builder.myClassName;
        myDisplayName = builder.myDisplayName;
        myRecipes = builder.myRecipes;
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

    public List<ItemRecipe> getRecipes() {
        return myRecipes;
    }

    public static class Builder {
        private String myClassName;
        private String myDisplayName;
        private List<ItemRecipe> myRecipes;

        private Builder() {
            // Empty
        }

        public Builder withClassName(String className) {
            myClassName = className;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            myDisplayName = displayName;
            return this;
        }

        public Builder withRecipes(List<ItemRecipe> recipes) {
            myRecipes = recipes;
            return this;
        }

        public PlannerItem build() {
            return new PlannerItem(this);
        }
    }
}
