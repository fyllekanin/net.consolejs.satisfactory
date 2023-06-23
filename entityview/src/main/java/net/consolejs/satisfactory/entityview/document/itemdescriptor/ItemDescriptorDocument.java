package net.consolejs.satisfactory.entityview.document.itemdescriptor;

import com.google.gson.annotations.SerializedName;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;

import java.util.List;
import java.util.Objects;

public class ItemDescriptorDocument {
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
    @SerializedName("recipes")
    private final List<ItemRecipe> myRecipes;

    private ItemDescriptorDocument(Builder builder) {
        myGameVersion = builder.myGameVersion;
        myClassName = builder.myClassName;
        myDisplayName = builder.myDisplayName;
        myDescription = builder.myDescription;
        mySmallIcon = builder.mySmallIcon;
        myBigIcon = builder.myBigIcon;
        myResourceType = builder.myResourceType;
        myRecipes = builder.myRecipes;
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

    public List<ItemRecipe> getRecipes() {
        return myRecipes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ItemDescriptorDocument other = (ItemDescriptorDocument) obj;
        return Objects.equals(myGameVersion, other.myGameVersion) &&
                Objects.equals(myClassName, other.myClassName) &&
                Objects.equals(myDisplayName, other.myDisplayName) &&
                Objects.equals(myDescription, other.myDescription) &&
                Objects.equals(mySmallIcon, other.mySmallIcon) &&
                Objects.equals(myBigIcon, other.myBigIcon) &&
                myResourceType == other.myResourceType &&
                Objects.equals(myRecipes, other.myRecipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myGameVersion, myClassName, myDisplayName, myDescription, mySmallIcon, myBigIcon,
                myResourceType, myRecipes);
    }

    public static class Builder {
        private String myGameVersion;
        private String myClassName;
        private String myDisplayName;
        private String myDescription;
        private String mySmallIcon;
        private String myBigIcon;
        private ResourceType myResourceType;
        private List<ItemRecipe> myRecipes;

        private Builder() {
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

        public Builder withRecipes(List<ItemRecipe> recipes) {
            myRecipes = recipes;
            return this;
        }

        public ItemDescriptorDocument build() {
            return new ItemDescriptorDocument(this);
        }
    }
}
