package net.consolejs.satisfactory.restservice.planner.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PlannerManufacturer {
    @SerializedName("manufacturerClassName")
    private final String myManufacturerClassName;
    @SerializedName("icon")
    private final String myIcon;
    @SerializedName("displayName")
    private final String myDisplayName;
    @SerializedName("amount")
    private final float myAmount;

    private PlannerManufacturer(Builder builder) {
        myManufacturerClassName = builder.myManufacturerClassName;
        myIcon = builder.myIcon;
        myDisplayName = builder.myDisplayName;
        myAmount = builder.myAmount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getManufacturerClassName() {
        return myManufacturerClassName;
    }

    public String getIcon() {
        return myIcon;
    }

    public String getDisplayName() {
        return myDisplayName;
    }

    public float getAmount() {
        return myAmount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlannerManufacturer other = (PlannerManufacturer) obj;
        return Objects.equals(myManufacturerClassName, other.myManufacturerClassName)
                && Objects.equals(myIcon, other.myIcon)
                && Objects.equals(myDisplayName, other.myDisplayName)
                && Objects.equals(myAmount, other.myAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myManufacturerClassName, myIcon, myDisplayName, myAmount);
    }

    public static class Builder {
        private String myManufacturerClassName;
        private String myIcon;
        private String myDisplayName;
        private float myAmount;

        private Builder() {
        }

        public Builder withManufacturerClassName(String manufacturerClassName) {
            myManufacturerClassName = manufacturerClassName;
            return this;
        }

        public Builder withIcon(String icon) {
            myIcon = icon;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            myDisplayName = displayName;
            return this;
        }

        public Builder withAmount(float amount) {
            myAmount = amount;
            return this;
        }

        public PlannerManufacturer build() {
            return new PlannerManufacturer(this);
        }
    }
}
