package net.consolejs.satisfactory.restservice.planner.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class PlannerStep {
    @SerializedName("recipeClassName")
    private final String myRecipeClassName;
    @SerializedName("amount")
    private final float myAmount;
    @SerializedName("displayName")
    private final String myDisplayName;
    @SerializedName("manufacturer")
    private final PlannerManufacturer myManufacturer;
    @SerializedName("preSteps")
    private final List<PlannerStep> myPreSteps;

    private PlannerStep(Builder builder) {
        myRecipeClassName = builder.myRecipeClassName;
        myAmount = builder.myAmount;
        myDisplayName = builder.myDisplayName;
        myManufacturer = builder.myManufacturer;
        myPreSteps = builder.myPreSteps;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getRecipeClassName() {
        return myRecipeClassName;
    }

    public float getAmount() {
        return myAmount;
    }

    public String getDisplayName() {
        return myDisplayName;
    }

    public PlannerManufacturer getManufacturer() {
        return myManufacturer;
    }

    public List<PlannerStep> getPreSteps() {
        return myPreSteps;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlannerStep other) {
            return Objects.equals(myRecipeClassName, other.myRecipeClassName) &&
                    Objects.equals(myAmount, other.myAmount) &&
                    Objects.equals(myDisplayName, other.myDisplayName) &&
                    Objects.equals(myManufacturer, other.myManufacturer) &&
                    Objects.equals(myPreSteps, other.myPreSteps);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myRecipeClassName, myAmount, myDisplayName, myManufacturer, myPreSteps);
    }

    public static class Builder {
        private String myRecipeClassName;
        private float myAmount;
        private String myDisplayName;
        private PlannerManufacturer myManufacturer;
        private List<PlannerStep> myPreSteps;

        private Builder() {
        }

        public Builder withRecipeClassName(String recipeClassName) {
            myRecipeClassName = recipeClassName;
            return this;
        }

        public Builder withAmount(float amount) {
            myAmount = amount;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            myDisplayName = displayName;
            return this;
        }

        public Builder withManufacturer(PlannerManufacturer manufacturer) {
            myManufacturer = manufacturer;
            return this;
        }

        public Builder withPreSteps(List<PlannerStep> preSteps) {
            myPreSteps = preSteps;
            return this;
        }

        public PlannerStep build() {
            return new PlannerStep(this);
        }
    }
}
