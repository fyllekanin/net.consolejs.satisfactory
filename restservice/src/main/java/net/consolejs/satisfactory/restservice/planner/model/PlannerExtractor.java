package net.consolejs.satisfactory.restservice.planner.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PlannerExtractor {
    @SerializedName("extractorClassName")
    private final String myExtractorClassName;
    @SerializedName("icon")
    private final String myIcon;
    @SerializedName("displayName")
    private final String myDisplayName;

    private PlannerExtractor(Builder builder) {
        myExtractorClassName = builder.myExtractorClassName;
        myIcon = builder.myIcon;
        myDisplayName = builder.myDisplayName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getExtractorClassName() {
        return myExtractorClassName;
    }

    public String getIcon() {
        return myIcon;
    }

    public String getDisplayName() {
        return myDisplayName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlannerExtractor other = (PlannerExtractor) obj;
        return Objects.equals(myExtractorClassName, other.myExtractorClassName)
                && Objects.equals(myIcon, other.myIcon)
                && Objects.equals(myDisplayName, other.myDisplayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myExtractorClassName, myIcon, myDisplayName);
    }

    public static class Builder {
        private String myExtractorClassName;
        private String myIcon;
        private String myDisplayName;

        private Builder() {
        }

        public Builder withExtractorClassName(String manufacturerClassName) {
            myExtractorClassName = manufacturerClassName;
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

        public PlannerExtractor build() {
            return new PlannerExtractor(this);
        }
    }
}
