package net.consolejs.satisfactory.entityview.document.extractor;

import com.google.gson.annotations.SerializedName;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;

import java.util.List;
import java.util.Objects;

public class ExtractorDocument {
    @SerializedName("gameVersion")
    private final String myGameVersion;
    @SerializedName("className")
    private final String myClassName;
    @SerializedName("resourceType")
    private final ResourceType myResourceType;
    @SerializedName("displayName")
    private final String myDisplayName;
    @SerializedName("description")
    private final String myDescription;
    @SerializedName("allowedResources")
    private final List<String> myAllowedResources;
    @SerializedName("smallIcon")
    private final String mySmallIcon;
    @SerializedName("bigIcon")
    private final String myBigIcon;

    public ExtractorDocument(Builder builder) {
        myGameVersion = builder.myGameVersion;
        myClassName = builder.myClassName;
        myResourceType = builder.myResourceType;
        myDisplayName = builder.myDisplayName;
        myDescription = builder.myDescription;
        myAllowedResources = builder.myAllowedResources;
        mySmallIcon = builder.mySmallIcon;
        myBigIcon = builder.myBigIcon;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getGameVersion() {
        return myGameVersion;
    }

    public String getClassName() {
        return myClassName;
    }

    public ResourceType getResourceType() {
        return myResourceType;
    }

    public String getDisplayName() {
        return myDisplayName;
    }

    public String getDescription() {
        return myDescription;
    }

    public List<String> getAllowedResources() {
        return myAllowedResources;
    }

    public String getSmallIcon() {
        return mySmallIcon;
    }

    public String getBigIcon() {
        return myBigIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExtractorDocument other = (ExtractorDocument) o;
        return Objects.equals(myGameVersion, other.myGameVersion) &&
                Objects.equals(myClassName, other.myClassName) &&
                Objects.equals(myDisplayName, other.myDisplayName) &&
                Objects.equals(myDescription, other.myDescription) &&
                Objects.equals(myResourceType, other.myResourceType) &&
                Objects.equals(myAllowedResources, other.myAllowedResources) &&
                Objects.equals(mySmallIcon, other.mySmallIcon) &&
                Objects.equals(myBigIcon, other.myBigIcon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                myGameVersion,
                myClassName,
                myDisplayName,
                myDescription,
                myResourceType,
                myAllowedResources,
                mySmallIcon,
                myBigIcon
        );
    }

    public static class Builder {
        private String myGameVersion;
        private String myClassName;
        private ResourceType myResourceType;
        private String myDisplayName;
        private String myDescription;
        private List<String> myAllowedResources;
        private String mySmallIcon;
        private String myBigIcon;

        private Builder() {
            // Empty
        }

        public Builder withGameVersion(String gameVersion) {
            myGameVersion = gameVersion;
            return this;
        }

        public Builder withClassName(String className) {
            myClassName = className;
            return this;
        }

        public Builder withResourceType(ResourceType resourceType) {
            myResourceType = resourceType;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            myDisplayName = displayName;
            return this;
        }

        public Builder withDescription(String description) {
            myDescription = description;
            return this;
        }

        public Builder withAllowedResources(List<String> allowedResources) {
            myAllowedResources = allowedResources;
            return this;
        }

        public Builder withSmallIcon(String smallIcon) {
            mySmallIcon = smallIcon;
            return this;
        }

        public Builder withBigIcon(String bigIcon) {
            myBigIcon = bigIcon;
            return this;
        }

        public ExtractorDocument build() {
            return new ExtractorDocument(this);
        }
    }
}
