package net.consolejs.satisfactory.entityview.document.resource;

import com.google.gson.annotations.SerializedName;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;

import java.util.Objects;

public class ResourceDocument {
    @SerializedName("gameVersion")
    private final String myGameVersion;
    @SerializedName("className")
    private final String myClassName;
    @SerializedName("displayName")
    private final String myDisplayName;
    @SerializedName("description")
    private final String myDescription;
    @SerializedName("smallIcon")
    private final String mySmallIcon;
    @SerializedName("bigIcon")
    private final String myBigIcon;
    @SerializedName("resourceType")
    private final ResourceType myResourceType;

    private ResourceDocument(Builder builder) {
        myGameVersion = builder.myGameVersion;
        myClassName = builder.myClassName;
        myDisplayName = builder.myDisplayName;
        myDescription = builder.myDescription;
        mySmallIcon = builder.mySmallIcon;
        myBigIcon = builder.myBigIcon;
        myResourceType = builder.myResourceType;
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

    public String getDisplayName() {
        return myDisplayName;
    }

    public String getDescription() {
        return myDescription;
    }

    public String getSmallIcon() {
        return mySmallIcon;
    }

    public String getBigIcon() {
        return myBigIcon;
    }

    public ResourceType getResourceType() {
        return myResourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResourceDocument other = (ResourceDocument) o;
        return Objects.equals(myGameVersion, other.myGameVersion) &&
                Objects.equals(myClassName, other.myClassName) &&
                Objects.equals(myDisplayName, other.myDisplayName) &&
                Objects.equals(myDescription, other.myDescription) &&
                Objects.equals(mySmallIcon, other.mySmallIcon) &&
                Objects.equals(myBigIcon, other.myBigIcon) &&
                Objects.equals(myResourceType, other.myResourceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                myGameVersion,
                myClassName,
                myDisplayName,
                myDescription,
                mySmallIcon,
                myBigIcon,
                myResourceType
        );
    }

    public static class Builder {
        private String myGameVersion;
        private String myClassName;
        private String myDisplayName;
        private String myDescription;
        private String mySmallIcon;
        private String myBigIcon;
        private ResourceType myResourceType;

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

        public Builder withDisplayName(String displayName) {
            myDisplayName = displayName;
            return this;
        }

        public Builder withDescription(String description) {
            myDescription = description;
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

        public Builder withResourceType(ResourceType resourceType) {
            myResourceType = resourceType;
            return this;
        }

        public ResourceDocument build() {
            return new ResourceDocument(this);
        }
    }
}
