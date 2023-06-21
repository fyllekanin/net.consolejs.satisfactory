package net.consolejs.satisfactory.restservice.satisfactoryimport.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SatisfactoryClass {
    @SerializedName("ClassName")
    private final String myClassName;
    @SerializedName("FullName")
    private final String myFullName;

    @SerializedName("mPlayFireEffects")
    private final String myPlayFireEffects;

    @SerializedName("AmmoFiredDelegate")
    private final String myAmmoFiredDelegate;

    @SerializedName("mFiringTransform")
    private final String myFiringTransform;

    @SerializedName("mFiringDirection")
    private final String myFiringDirection;

    @SerializedName("mMagazineSize")
    private final String myMagazineSize;

    @SerializedName("mMaxAmmoEffectiveRange")
    private final String myMaxAmmoEffectiveRange;

    @SerializedName("mReloadTimeMultiplier")
    private final String myReloadTimeMultiplier;

    @SerializedName("mFireRate")
    private final String myFireRate;

    @SerializedName("mFiringTransformIgnoresDispersion")
    private final String myFiringTransformIgnoresDispersion;

    @SerializedName("mDispersionFireRateMultiplier")
    private final String myDispersionFireRateMultiplier;

    @SerializedName("mDispersionPerShot")
    private final String myDispersionPerShot;

    @SerializedName("mRestingDispersion")
    private final String myRestingDispersion;

    @SerializedName("mFiringDispersion")
    private final String myFiringDispersion;

    @SerializedName("mDispersionRecoveryTime")
    private final String myDispersionRecoveryTime;

    @SerializedName("mHasBeenInitialized")
    private final String myHasBeenInitialized;

    @SerializedName("mWeaponDamageMultiplier")
    private final String myWeaponDamageMultiplier;

    @SerializedName("mMagazineMeshMaterials")
    private final String myMagazineMeshMaterials;

    @SerializedName("mDamageTypesOnImpact")
    private final String myDamageTypesOnImpact;

    @SerializedName("mAmmoDamageFalloff")
    private final String myAmmoDamageFalloff;

    @SerializedName("mMuzzleFlashScale")
    private final String myMuzzleFlashScale;

    @SerializedName("mFiringSounds")
    private final String myFiringSounds;

    @SerializedName("mAmmoColor")
    private final String myAmmoColor;

    @SerializedName("mAmmoScale")
    private final String myAmmoScale;

    @SerializedName("mAmmoTickFunction")
    private final String myAmmoTickFunction;

    @SerializedName("mDisplayName")
    private final String myDisplayName;

    @SerializedName("mDescription")
    private final String myDescription;

    @SerializedName("mAbbreviatedDisplayName")
    private final String myAbbreviatedDisplayName;

    @SerializedName("mStackSize")
    private final String myStackSize;

    @SerializedName("mCanBeDiscarded")
    private final String myCanBeDiscarded;

    @SerializedName("mRememberPickUp")
    private final String myRememberPickUp;

    @SerializedName("mEnergyValue")
    private final String myEnergyValue;

    @SerializedName("mRadioactiveDecay")
    private final String myRadioactiveDecay;

    @SerializedName("mForm")
    private final String myForm;

    @SerializedName("mSmallIcon")
    private final String mySmallIcon;

    @SerializedName("mPersistentBigIcon")
    private final String myPersistentBigIcon;

    @SerializedName("mCrosshairMaterial")
    private final String myCrosshairMaterial;

    @SerializedName("mDescriptorStatBars")
    private final String myDescriptorStatBars;

    @SerializedName("mSubCategories")
    private final String mySubCategories;

    @SerializedName("mMenuPriority")
    private final String myMenuPriority;

    @SerializedName("mFluidColor")
    private final String myFluidColor;

    @SerializedName("mGasColor")
    private final String myGasColor;

    @SerializedName("mCompatibleItemDescriptors")
    private final String myCompatibleItemDescriptors;

    @SerializedName("mClassToScanFor")
    private final String myClassToScanFor;

    @SerializedName("mScannableType")
    private final String myScannableType;

    @SerializedName("mShouldOverrideScannerDisplayText")
    private final String myShouldOverrideScannerDisplayText;

    @SerializedName("mScannerDisplayText")
    private final String myScannerDisplayText;

    @SerializedName("mScannerLightColor")
    private final String myScannerLightColor;

    @SerializedName("mResourceSinkPoints")
    private final String myResourceSinkPoints;
    @SerializedName("mIngredients")
    private final String myIngredients;
    @SerializedName("mManufactoringDuration")
    private final float myManufactoringDuration;

    private SatisfactoryClass(Builder builder) {
        myClassName = builder.myClassName;
        myFullName = builder.myFullName;
        myPlayFireEffects = builder.myPlayFireEffects;
        myAmmoFiredDelegate = builder.myAmmoFiredDelegate;
        myFiringTransform = builder.myFiringTransform;
        myFiringDirection = builder.myFiringDirection;
        myMagazineSize = builder.myMagazineSize;
        myMaxAmmoEffectiveRange = builder.myMaxAmmoEffectiveRange;
        myReloadTimeMultiplier = builder.myReloadTimeMultiplier;
        myFireRate = builder.myFireRate;
        myFiringTransformIgnoresDispersion = builder.myFiringTransformIgnoresDispersion;
        myDispersionFireRateMultiplier = builder.myDispersionFireRateMultiplier;
        myDispersionPerShot = builder.myDispersionPerShot;
        myRestingDispersion = builder.myRestingDispersion;
        myFiringDispersion = builder.myFiringDispersion;
        myDispersionRecoveryTime = builder.myDispersionRecoveryTime;
        myHasBeenInitialized = builder.myHasBeenInitialized;
        myWeaponDamageMultiplier = builder.myWeaponDamageMultiplier;
        myMagazineMeshMaterials = builder.myMagazineMeshMaterials;
        myDamageTypesOnImpact = builder.myDamageTypesOnImpact;
        myAmmoDamageFalloff = builder.myAmmoDamageFalloff;
        myMuzzleFlashScale = builder.myMuzzleFlashScale;
        myFiringSounds = builder.myFiringSounds;
        myAmmoColor = builder.myAmmoColor;
        myAmmoScale = builder.myAmmoScale;
        myAmmoTickFunction = builder.myAmmoTickFunction;
        myDisplayName = builder.myDisplayName;
        myDescription = builder.myDescription;
        myAbbreviatedDisplayName = builder.myAbbreviatedDisplayName;
        myStackSize = builder.myStackSize;
        myCanBeDiscarded = builder.myCanBeDiscarded;
        myRememberPickUp = builder.myRememberPickUp;
        myEnergyValue = builder.myEnergyValue;
        myRadioactiveDecay = builder.myRadioactiveDecay;
        myForm = builder.myForm;
        mySmallIcon = builder.mySmallIcon;
        myPersistentBigIcon = builder.myPersistentBigIcon;
        myCrosshairMaterial = builder.myCrosshairMaterial;
        myDescriptorStatBars = builder.myDescriptorStatBars;
        mySubCategories = builder.mySubCategories;
        myMenuPriority = builder.myMenuPriority;
        myFluidColor = builder.myFluidColor;
        myGasColor = builder.myGasColor;
        myCompatibleItemDescriptors = builder.myCompatibleItemDescriptors;
        myClassToScanFor = builder.myClassToScanFor;
        myScannableType = builder.myScannableType;
        myShouldOverrideScannerDisplayText = builder.myShouldOverrideScannerDisplayText;
        myScannerDisplayText = builder.myScannerDisplayText;
        myScannerLightColor = builder.myScannerLightColor;
        myResourceSinkPoints = builder.myResourceSinkPoints;
        myIngredients = builder.myIngredients;
        myManufactoringDuration = builder.myManufactoringDuration;
    }


    public String getClassName() {
        return myClassName;
    }

    public String getFullName() {
        return myFullName;
    }

    public String getPlayFireEffects() {
        return myPlayFireEffects;
    }

    public String getAmmoFiredDelegate() {
        return myAmmoFiredDelegate;
    }

    public String getFiringTransform() {
        return myFiringTransform;
    }

    public String getFiringDirection() {
        return myFiringDirection;
    }

    public String getMagazineSize() {
        return myMagazineSize;
    }

    public String getMaxAmmoEffectiveRange() {
        return myMaxAmmoEffectiveRange;
    }

    public String getReloadTimeMultiplier() {
        return myReloadTimeMultiplier;
    }

    public String getFireRate() {
        return myFireRate;
    }

    public String getFiringTransformIgnoresDispersion() {
        return myFiringTransformIgnoresDispersion;
    }

    public String getDispersionFireRateMultiplier() {
        return myDispersionFireRateMultiplier;
    }

    public String getDispersionPerShot() {
        return myDispersionPerShot;
    }

    public String getRestingDispersion() {
        return myRestingDispersion;
    }

    public String getFiringDispersion() {
        return myFiringDispersion;
    }

    public String getDispersionRecoveryTime() {
        return myDispersionRecoveryTime;
    }

    public String getHasBeenInitialized() {
        return myHasBeenInitialized;
    }

    public String getWeaponDamageMultiplier() {
        return myWeaponDamageMultiplier;
    }

    public String getMagazineMeshMaterials() {
        return myMagazineMeshMaterials;
    }

    public String getDamageTypesOnImpact() {
        return myDamageTypesOnImpact;
    }

    public String getAmmoDamageFalloff() {
        return myAmmoDamageFalloff;
    }

    public String getMuzzleFlashScale() {
        return myMuzzleFlashScale;
    }

    public String getFiringSounds() {
        return myFiringSounds;
    }

    public String getAmmoColor() {
        return myAmmoColor;
    }

    public String getAmmoScale() {
        return myAmmoScale;
    }

    public String getAmmoTickFunction() {
        return myAmmoTickFunction;
    }

    public String getDisplayName() {
        return myDisplayName;
    }

    public String getDescription() {
        return myDescription;
    }

    public String getAbbreviatedDisplayName() {
        return myAbbreviatedDisplayName;
    }

    public String getStackSize() {
        return myStackSize;
    }

    public String getCanBeDiscarded() {
        return myCanBeDiscarded;
    }

    public String getRememberPickUp() {
        return myRememberPickUp;
    }

    public String getEnergyValue() {
        return myEnergyValue;
    }

    public String getRadioactiveDecay() {
        return myRadioactiveDecay;
    }

    public String getForm() {
        return myForm;
    }

    public String getSmallIcon() {
        return mySmallIcon;
    }

    public String getPersistentBigIcon() {
        return myPersistentBigIcon;
    }

    public String getCrosshairMaterial() {
        return myCrosshairMaterial;
    }

    public String getDescriptorStatBars() {
        return myDescriptorStatBars;
    }

    public String getSubCategories() {
        return mySubCategories;
    }

    public String getMenuPriority() {
        return myMenuPriority;
    }

    public String getFluidColor() {
        return myFluidColor;
    }

    public String getGasColor() {
        return myGasColor;
    }

    public String getCompatibleItemDescriptors() {
        return myCompatibleItemDescriptors;
    }

    public String getClassToScanFor() {
        return myClassToScanFor;
    }

    public String getScannableType() {
        return myScannableType;
    }

    public String getShouldOverrideScannerDisplayText() {
        return myShouldOverrideScannerDisplayText;
    }

    public String getScannerDisplayText() {
        return myScannerDisplayText;
    }

    public String getScannerLightColor() {
        return myScannerLightColor;
    }

    public String getResourceSinkPoints() {
        return myResourceSinkPoints;
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
        private String myPlayFireEffects;
        private String myAmmoFiredDelegate;
        private String myFiringTransform;
        private String myFiringDirection;
        private String myMagazineSize;
        private String myMaxAmmoEffectiveRange;
        private String myReloadTimeMultiplier;
        private String myFireRate;
        private String myFiringTransformIgnoresDispersion;
        private String myDispersionFireRateMultiplier;
        private String myDispersionPerShot;
        private String myRestingDispersion;
        private String myFiringDispersion;
        private String myDispersionRecoveryTime;
        private String myHasBeenInitialized;
        private String myWeaponDamageMultiplier;
        private String myMagazineMeshMaterials;
        private String myDamageTypesOnImpact;
        private String myAmmoDamageFalloff;
        private String myMuzzleFlashScale;
        private String myFiringSounds;
        private String myAmmoColor;
        private String myAmmoScale;
        private String myAmmoTickFunction;
        private String myDisplayName;
        private String myDescription;
        private String myAbbreviatedDisplayName;
        private String myStackSize;
        private String myCanBeDiscarded;
        private String myRememberPickUp;
        private String myEnergyValue;
        private String myRadioactiveDecay;
        private String myForm;
        private String mySmallIcon;
        private String myPersistentBigIcon;
        private String myCrosshairMaterial;
        private String myDescriptorStatBars;
        private String mySubCategories;
        private String myMenuPriority;
        private String myFluidColor;
        private String myGasColor;
        private String myCompatibleItemDescriptors;
        private String myClassToScanFor;
        private String myScannableType;
        private String myShouldOverrideScannerDisplayText;
        private String myScannerDisplayText;
        private String myScannerLightColor;
        private String myResourceSinkPoints;
        private String myIngredients;
        private float myManufactoringDuration;

        private Builder() {
        }

        private Builder(SatisfactoryClass original) {
            myClassName = original.myClassName;
            myFullName = original.myFullName;
            myPlayFireEffects = original.myPlayFireEffects;
            myAmmoFiredDelegate = original.myAmmoFiredDelegate;
            myFiringTransform = original.myFiringTransform;
            myFiringDirection = original.myFiringDirection;
            myMagazineSize = original.myMagazineSize;
            myMaxAmmoEffectiveRange = original.myMaxAmmoEffectiveRange;
            myReloadTimeMultiplier = original.myReloadTimeMultiplier;
            myFireRate = original.myFireRate;
            myFiringTransformIgnoresDispersion = original.myFiringTransformIgnoresDispersion;
            myDispersionFireRateMultiplier = original.myDispersionFireRateMultiplier;
            myDispersionPerShot = original.myDispersionPerShot;
            myRestingDispersion = original.myRestingDispersion;
            myFiringDispersion = original.myFiringDispersion;
            myDispersionRecoveryTime = original.myDispersionRecoveryTime;
            myHasBeenInitialized = original.myHasBeenInitialized;
            myWeaponDamageMultiplier = original.myWeaponDamageMultiplier;
            myMagazineMeshMaterials = original.myMagazineMeshMaterials;
            myDamageTypesOnImpact = original.myDamageTypesOnImpact;
            myAmmoDamageFalloff = original.myAmmoDamageFalloff;
            myMuzzleFlashScale = original.myMuzzleFlashScale;
            myFiringSounds = original.myFiringSounds;
            myAmmoColor = original.myAmmoColor;
            myAmmoScale = original.myAmmoScale;
            myAmmoTickFunction = original.myAmmoTickFunction;
            myDisplayName = original.myDisplayName;
            myDescription = original.myDescription;
            myAbbreviatedDisplayName = original.myAbbreviatedDisplayName;
            myStackSize = original.myStackSize;
            myCanBeDiscarded = original.myCanBeDiscarded;
            myRememberPickUp = original.myRememberPickUp;
            myEnergyValue = original.myEnergyValue;
            myRadioactiveDecay = original.myRadioactiveDecay;
            myForm = original.myForm;
            mySmallIcon = original.mySmallIcon;
            myPersistentBigIcon = original.myPersistentBigIcon;
            myCrosshairMaterial = original.myCrosshairMaterial;
            myDescriptorStatBars = original.myDescriptorStatBars;
            mySubCategories = original.mySubCategories;
            myMenuPriority = original.myMenuPriority;
            myFluidColor = original.myFluidColor;
            myGasColor = original.myGasColor;
            myCompatibleItemDescriptors = original.myCompatibleItemDescriptors;
            myClassToScanFor = original.myClassToScanFor;
            myScannableType = original.myScannableType;
            myShouldOverrideScannerDisplayText = original.myShouldOverrideScannerDisplayText;
            myScannerDisplayText = original.myScannerDisplayText;
            myScannerLightColor = original.myScannerLightColor;
            myResourceSinkPoints = original.myResourceSinkPoints;
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

        public Builder withPlayFireEffects(String playFireEffects) {
            myPlayFireEffects = playFireEffects;
            return this;
        }

        public Builder withAmmoFiredDelegate(String ammoFiredDelegate) {
            myAmmoFiredDelegate = ammoFiredDelegate;
            return this;
        }

        public Builder withFiringTransform(String firingTransform) {
            myFiringTransform = firingTransform;
            return this;
        }

        public Builder withFiringDirection(String firingDirection) {
            myFiringDirection = firingDirection;
            return this;
        }

        public Builder withMagazineSize(String magazineSize) {
            myMagazineSize = magazineSize;
            return this;
        }

        public Builder withMaxAmmoEffectiveRange(String maxAmmoEffectiveRange) {
            myMaxAmmoEffectiveRange = maxAmmoEffectiveRange;
            return this;
        }

        public Builder withReloadTimeMultiplier(String reloadTimeMultiplier) {
            myReloadTimeMultiplier = reloadTimeMultiplier;
            return this;
        }

        public Builder withFireRate(String fireRate) {
            myFireRate = fireRate;
            return this;
        }

        public Builder withFiringTransformIgnoresDispersion(String firingTransformIgnoresDispersion) {
            myFiringTransformIgnoresDispersion = firingTransformIgnoresDispersion;
            return this;
        }

        public Builder withDispersionFireRateMultiplier(String dispersionFireRateMultiplier) {
            myDispersionFireRateMultiplier = dispersionFireRateMultiplier;
            return this;
        }

        public Builder withDispersionPerShot(String dispersionPerShot) {
            myDispersionPerShot = dispersionPerShot;
            return this;
        }

        public Builder withRestingDispersion(String restingDispersion) {
            myRestingDispersion = restingDispersion;
            return this;
        }

        public Builder withFiringDispersion(String firingDispersion) {
            myFiringDispersion = firingDispersion;
            return this;
        }

        public Builder withDispersionRecoveryTime(String dispersionRecoveryTime) {
            myDispersionRecoveryTime = dispersionRecoveryTime;
            return this;
        }

        public Builder withHasBeenInitialized(String hasBeenInitialized) {
            myHasBeenInitialized = hasBeenInitialized;
            return this;
        }

        public Builder withWeaponDamageMultiplier(String weaponDamageMultiplier) {
            myWeaponDamageMultiplier = weaponDamageMultiplier;
            return this;
        }

        public Builder withMagazineMeshMaterials(String magazineMeshMaterials) {
            myMagazineMeshMaterials = magazineMeshMaterials;
            return this;
        }

        public Builder withDamageTypesOnImpact(String damageTypesOnImpact) {
            myDamageTypesOnImpact = damageTypesOnImpact;
            return this;
        }

        public Builder withAmmoDamageFalloff(String ammoDamageFalloff) {
            myAmmoDamageFalloff = ammoDamageFalloff;
            return this;
        }

        public Builder withMuzzleFlashScale(String muzzleFlashScale) {
            myMuzzleFlashScale = muzzleFlashScale;
            return this;
        }

        public Builder withFiringSounds(String firingSounds) {
            myFiringSounds = firingSounds;
            return this;
        }

        public Builder withAmmoColor(String ammoColor) {
            myAmmoColor = ammoColor;
            return this;
        }

        public Builder withAmmoScale(String ammoScale) {
            myAmmoScale = ammoScale;
            return this;
        }

        public Builder withAmmoTickFunction(String ammoTickFunction) {
            myAmmoTickFunction = ammoTickFunction;
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

        public Builder withAbbreviatedDisplayName(String abbreviatedDisplayName) {
            myAbbreviatedDisplayName = abbreviatedDisplayName;
            return this;
        }

        public Builder withStackSize(String stackSize) {
            myStackSize = stackSize;
            return this;
        }

        public Builder withCanBeDiscarded(String canBeDiscarded) {
            myCanBeDiscarded = canBeDiscarded;
            return this;
        }

        public Builder withRememberPickUp(String rememberPickUp) {
            myRememberPickUp = rememberPickUp;
            return this;
        }

        public Builder withEnergyValue(String energyValue) {
            myEnergyValue = energyValue;
            return this;
        }

        public Builder withRadioactiveDecay(String radioactiveDecay) {
            myRadioactiveDecay = radioactiveDecay;
            return this;
        }

        public Builder withForm(String form) {
            myForm = form;
            return this;
        }

        public Builder withSmallIcon(String smallIcon) {
            mySmallIcon = smallIcon;
            return this;
        }

        public Builder withPersistentBigIcon(String persistentBigIcon) {
            myPersistentBigIcon = persistentBigIcon;
            return this;
        }

        public Builder withCrosshairMaterial(String crosshairMaterial) {
            myCrosshairMaterial = crosshairMaterial;
            return this;
        }

        public Builder withDescriptorStatBars(String descriptorStatBars) {
            myDescriptorStatBars = descriptorStatBars;
            return this;
        }

        public Builder withSubCategories(String subCategories) {
            mySubCategories = subCategories;
            return this;
        }

        public Builder withMenuPriority(String menuPriority) {
            myMenuPriority = menuPriority;
            return this;
        }

        public Builder withFluidColor(String fluidColor) {
            myFluidColor = fluidColor;
            return this;
        }

        public Builder withGasColor(String gasColor) {
            myGasColor = gasColor;
            return this;
        }

        public Builder withCompatibleItemDescriptors(String compatibleItemDescriptors) {
            myCompatibleItemDescriptors = compatibleItemDescriptors;
            return this;
        }

        public Builder withClassToScanFor(String classToScanFor) {
            myClassToScanFor = classToScanFor;
            return this;
        }

        public Builder withScannableType(String scannableType) {
            myScannableType = scannableType;
            return this;
        }

        public Builder withShouldOverrideScannerDisplayText(String shouldOverrideScannerDisplayText) {
            myShouldOverrideScannerDisplayText = shouldOverrideScannerDisplayText;
            return this;
        }

        public Builder withScannerDisplayText(String scannerDisplayText) {
            myScannerDisplayText = scannerDisplayText;
            return this;
        }

        public Builder withScannerLightColor(String scannerLightColor) {
            myScannerLightColor = scannerLightColor;
            return this;
        }

        public Builder withResourceSinkPoints(String resourceSinkPoints) {
            myResourceSinkPoints = resourceSinkPoints;
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
                Objects.equals(getPlayFireEffects(), other.getPlayFireEffects()) &&
                Objects.equals(getAmmoFiredDelegate(), other.getAmmoFiredDelegate()) &&
                Objects.equals(getFiringTransform(), other.getFiringTransform()) &&
                Objects.equals(getFiringDirection(), other.getFiringDirection()) &&
                Objects.equals(getMagazineSize(), other.getMagazineSize()) &&
                Objects.equals(getMaxAmmoEffectiveRange(), other.getMaxAmmoEffectiveRange()) &&
                Objects.equals(getReloadTimeMultiplier(), other.getReloadTimeMultiplier()) &&
                Objects.equals(getFireRate(), other.getFireRate()) &&
                Objects.equals(getFiringTransformIgnoresDispersion(), other.getFiringTransformIgnoresDispersion()) &&
                Objects.equals(getDispersionFireRateMultiplier(), other.getDispersionFireRateMultiplier()) &&
                Objects.equals(getDispersionPerShot(), other.getDispersionPerShot()) &&
                Objects.equals(getRestingDispersion(), other.getRestingDispersion()) &&
                Objects.equals(getFiringDispersion(), other.getFiringDispersion()) &&
                Objects.equals(getDispersionRecoveryTime(), other.getDispersionRecoveryTime()) &&
                Objects.equals(getHasBeenInitialized(), other.getHasBeenInitialized()) &&
                Objects.equals(getWeaponDamageMultiplier(), other.getWeaponDamageMultiplier()) &&
                Objects.equals(getMagazineMeshMaterials(), other.getMagazineMeshMaterials()) &&
                Objects.equals(getDamageTypesOnImpact(), other.getDamageTypesOnImpact()) &&
                Objects.equals(getAmmoDamageFalloff(), other.getAmmoDamageFalloff()) &&
                Objects.equals(getMuzzleFlashScale(), other.getMuzzleFlashScale()) &&
                Objects.equals(getFiringSounds(), other.getFiringSounds()) &&
                Objects.equals(getAmmoColor(), other.getAmmoColor()) &&
                Objects.equals(getAmmoScale(), other.getAmmoScale()) &&
                Objects.equals(getAmmoTickFunction(), other.getAmmoTickFunction()) &&
                Objects.equals(getDisplayName(), other.getDisplayName()) &&
                Objects.equals(getDescription(), other.getDescription()) &&
                Objects.equals(getAbbreviatedDisplayName(), other.getAbbreviatedDisplayName()) &&
                Objects.equals(getStackSize(), other.getStackSize()) &&
                Objects.equals(getCanBeDiscarded(), other.getCanBeDiscarded()) &&
                Objects.equals(getRememberPickUp(), other.getRememberPickUp()) &&
                Objects.equals(getEnergyValue(), other.getEnergyValue()) &&
                Objects.equals(getRadioactiveDecay(), other.getRadioactiveDecay()) &&
                Objects.equals(getForm(), other.getForm()) &&
                Objects.equals(getSmallIcon(), other.getSmallIcon()) &&
                Objects.equals(getPersistentBigIcon(), other.getPersistentBigIcon()) &&
                Objects.equals(getCrosshairMaterial(), other.getCrosshairMaterial()) &&
                Objects.equals(getDescriptorStatBars(), other.getDescriptorStatBars()) &&
                Objects.equals(getSubCategories(), other.getSubCategories()) &&
                Objects.equals(getMenuPriority(), other.getMenuPriority()) &&
                Objects.equals(getFluidColor(), other.getFluidColor()) &&
                Objects.equals(getGasColor(), other.getGasColor()) &&
                Objects.equals(getCompatibleItemDescriptors(), other.getCompatibleItemDescriptors()) &&
                Objects.equals(getClassToScanFor(), other.getClassToScanFor()) &&
                Objects.equals(getScannableType(), other.getScannableType()) &&
                Objects.equals(getShouldOverrideScannerDisplayText(), other.getShouldOverrideScannerDisplayText()) &&
                Objects.equals(getScannerDisplayText(), other.getScannerDisplayText()) &&
                Objects.equals(getScannerLightColor(), other.getScannerLightColor()) &&
                Objects.equals(getResourceSinkPoints(), other.getResourceSinkPoints()) &&
                Objects.equals(getIngredients(), other.getIngredients()) &&
                Objects.equals(getManufactoringDuration(), other.getManufactoringDuration());
    }
}
