package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import com.google.gson.annotations.SerializedName;
import net.consolejs.satisfactory.entityview.satisfactory.ResourceType;

import java.util.List;
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
    @SerializedName("mDescription")
    private final String myDescription;
    @SerializedName("mSmallIcon")
    private final String mySmallIcon;
    @SerializedName("mPersistentBigIcon")
    private final String myBigIcon;
    @SerializedName("mProducedIn")
    private final String myProducedIn;
    @SerializedName("mAllowedResourceForms")
    private final String myAllowedResourceForms;
    @SerializedName("mAllowedResources")
    private final String myAllowedResources;
    @SerializedName("mForm")
    private final ResourceType myResourceType;
    @SerializedName("mProduct")
    private final String myProduct;

    @SerializedName("mUnlocks")
    private final List<SatisfactoryClassUnlocks> myUnlocks;

    private SatisfactoryClass(Builder builder) {
        myClassName = builder.myClassName;
        myFullName = builder.myFullName;
        myDisplayName = builder.myDisplayName;
        myIngredients = builder.myIngredients;
        myManufactoringDuration = builder.myManufacturingDuration;
        myDescription = builder.myDescription;
        mySmallIcon = builder.mySmallIcon;
        myBigIcon = builder.myBigIcon;
        myProducedIn = builder.myProducedIn;
        myResourceType = builder.myResourceType;
        myAllowedResourceForms = builder.myAllowedResourceForms;
        myAllowedResources = builder.myAllowedResources;
        myProduct = builder.myProduct;
        myUnlocks = builder.myUnlocks;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public String getDescription() {
        return myDescription;
    }

    public String getSmallIcon() {
        return mySmallIcon;
    }

    public String getBigIcon() {
        return myBigIcon;
    }

    public String getProducedIn() {
        return myProducedIn;
    }

    public ResourceType getResourceType() {
        return myResourceType;
    }

    public String getAllowedResourceForms() {
        return myAllowedResourceForms;
    }

    public String getAllowedResources() {
        return myAllowedResources;
    }

    public String getProduct() {
        return myProduct;
    }

    public List<SatisfactoryClassUnlocks> getUnlocks() {
        return myUnlocks;
    }

    public Builder newBuilderFromCurrent() {
        return new Builder(this);
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
                Objects.equals(getManufactoringDuration(), other.getManufactoringDuration()) &&
                Objects.equals(getDescription(), other.getDescription()) &&
                Objects.equals(getSmallIcon(), other.getSmallIcon()) &&
                Objects.equals(getBigIcon(), other.getBigIcon()) &&
                Objects.equals(getProducedIn(), other.getProducedIn()) &&
                Objects.equals(getResourceType(), other.getResourceType()) &&
                Objects.equals(getAllowedResourceForms(), other.getAllowedResourceForms()) &&
                Objects.equals(getAllowedResources(), other.getAllowedResources()) &&
                Objects.equals(getProduct(), other.getProduct()) &&
                Objects.equals(getUnlocks(), other.getUnlocks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                myClassName,
                myFullName,
                myDisplayName,
                myIngredients,
                myManufactoringDuration,
                myDescription,
                mySmallIcon,
                myBigIcon,
                myProducedIn,
                myResourceType,
                myAllowedResourceForms,
                myAllowedResources,
                myProduct,
                myUnlocks
        );
    }

    public static class Builder {
        private String myClassName;
        private String myFullName;
        private String myDisplayName;
        private String myIngredients;
        private float myManufacturingDuration;
        private String myDescription;
        private String mySmallIcon;
        private String myBigIcon;
        private String myProducedIn;
        private ResourceType myResourceType;
        private String myAllowedResourceForms;
        private String myAllowedResources;
        private String myProduct;
        private List<SatisfactoryClassUnlocks> myUnlocks;

        private Builder() {
        }

        private Builder(SatisfactoryClass original) {
            myClassName = original.myClassName;
            myFullName = original.myFullName;
            myDisplayName = original.myDisplayName;
            myIngredients = original.myIngredients;
            myManufacturingDuration = original.myManufactoringDuration;
            myDescription = original.myDescription;
            mySmallIcon = original.mySmallIcon;
            myBigIcon = original.myBigIcon;
            myProducedIn = original.myProducedIn;
            myResourceType = original.myResourceType;
            myAllowedResourceForms = original.myAllowedResourceForms;
            myAllowedResources = original.myAllowedResources;
            myProduct = original.myProduct;
            myUnlocks = original.myUnlocks;
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
            myManufacturingDuration = manufactoringDuration;
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

        public Builder withProducedIn(String producedIn) {
            myProducedIn = producedIn;
            return this;
        }

        public Builder withResourceType(ResourceType resourceType) {
            myResourceType = resourceType;
            return this;
        }

        public Builder withAllowedResourceForms(String allowedResourceForms) {
            myAllowedResourceForms = allowedResourceForms;
            return this;
        }

        public Builder withAllowedResources(String allowedResources) {
            myAllowedResources = allowedResources;
            return this;
        }

        public Builder withProduct(String product) {
            myProduct = product;
            return this;
        }

        public Builder withUnlocks(List<SatisfactoryClassUnlocks> unlocks) {
            myUnlocks = unlocks;
            return this;
        }

        public SatisfactoryClass build() {
            return new SatisfactoryClass(this);
        }
    }
}
