package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SatisfactoryClass {
    @SerializedName("ClassName")
    private final String myClassName;
    @SerializedName("FullName")
    private final String myFullName;
    @SerializedName("mDisplayName")
    private final String myDisplayName;
    @SerializedName("mIngredients")
    private final String myIngredients;
    @SerializedName("mManufactoringDuration")
    private final float myManufactoringDuration;

    private SatisfactoryClass(Builder builder) {
        myClassName = builder.myClassName;
        myFullName = builder.myFullName;
        myDisplayName = builder.myDisplayName;
        myIngredients = builder.myIngredients;
        myManufactoringDuration = builder.myManufactoringDuration;
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

    public float getManufactoringDuration() {
        return myManufactoringDuration;
    }

    public String getIngredients() {
        return myIngredients;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder newBuilderFromCurrent() {
        return new Builder(this);
    }

    public static class Builder {
        private String myClassName;
        private String myFullName;
        private String myDisplayName;
        private String myIngredients;
        private float myManufactoringDuration;

        private Builder() {
        }

        private Builder(SatisfactoryClass original) {
            myClassName = original.myClassName;
            myFullName = original.myFullName;
            myDisplayName = original.myDisplayName;
            myIngredients = original.myIngredients;
            myManufactoringDuration = original.myManufactoringDuration;
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

        public Builder withIngredients(String ingredients) {
            myIngredients = ingredients;
            return this;
        }

        public Builder withManufactoringDuration(float manufactoringDuration) {
            myManufactoringDuration = manufactoringDuration;
            return this;
        }

        public SatisfactoryClass build() {
            return new SatisfactoryClass(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SatisfactoryClass other = (SatisfactoryClass) obj;

        return Objects.equals(getClassName(), other.getClassName()) &&
                Objects.equals(getFullName(), other.getFullName()) &&
                Objects.equals(getDisplayName(), other.getDisplayName()) &&
                Objects.equals(getIngredients(), other.getIngredients()) &&
                Objects.equals(getManufactoringDuration(), other.getManufactoringDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(myClassName, myFullName, myDisplayName, myIngredients, myManufactoringDuration);
    }
}
