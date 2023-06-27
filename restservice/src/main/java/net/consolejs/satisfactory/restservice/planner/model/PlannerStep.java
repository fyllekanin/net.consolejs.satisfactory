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
    @SerializedName("extractor")
    private final PlannerExtractor myExtractor;
    @SerializedName("preSteps")
    private final List<PlannerStep> myPreSteps;
    @SerializedName("icon")
    private final String myIcon;

    private PlannerStep(Builder builder) {
        myRecipeClassName = builder.myRecipeClassName;
        myAmount = builder.myAmount;
        myDisplayName = builder.myDisplayName;
        myManufacturer = builder.myManufacturer;
        myPreSteps = builder.myPreSteps;
        myExtractor = builder.myExtractor;
        myIcon = builder.myIcon;
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

    public PlannerExtractor getExtractor() {
        return myExtractor;
    }

    public List<PlannerStep> getPreSteps() {
        return myPreSteps;
    }

    public String getIcon() {
        return myIcon;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlannerStep other) {
            return Objects.equals(myRecipeClassName, other.myRecipeClassName) &&
                    Objects.equals(myAmount, other.myAmount) &&
                    Objects.equals(myDisplayName, other.myDisplayName) &&
                    Objects.equals(myManufacturer, other.myManufacturer) &&
                    Objects.equals(myPreSteps, other.myPreSteps) &&
                    Objects.equals(myExtractor, other.myExtractor) &&
                    Objects.equals(myIcon, other.myIcon);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myRecipeClassName, myAmount, myDisplayName, myManufacturer, myPreSteps, myExtractor,
                            myIcon);
    }

    public static class Builder {
        private String myRecipeClassName;
        private float myAmount;
        private String myDisplayName;
        private PlannerManufacturer myManufacturer;
        private List<PlannerStep> myPreSteps;
        private PlannerExtractor myExtractor;
        private String myIcon;

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

        public Builder withExtractor(PlannerExtractor extractor) {
            myExtractor = extractor;
            return this;
        }

        public Builder withIcon(String icon) {
            myIcon = icon;
            return this;
        }

        public PlannerStep build() {
            return new PlannerStep(this);
        }
    }
}
