package net.consolejs.restservice.satisfactoryimport;

import net.consolejs.restservice.satisfactoryimport.model.SatisfactoryClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSatisfactoryClass {

    @Test
    public void testShouldEqual() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();

        // When
        SatisfactoryClass first = builder.build();
        SatisfactoryClass second = first.newBuilderFromCurrent()
                .build();

        // Then
        assertEquals(first, second);
    }

    @Test
    public void testShouldNotEqual() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();

        // When
        SatisfactoryClass first = builder.build();
        SatisfactoryClass second = first.newBuilderFromCurrent()
                .withResourceSinkPoints("different")
                .build();

        // Then
        assertNotEquals(first, second);
    }

    @Test
    public void testBuilder() {
        // Given
        SatisfactoryClass.Builder builder = getBuilderWithAllPropertiesFilled();

        // When
        SatisfactoryClass obj = builder.build();

        // Then
        assertNotNull(obj);
        assertEquals("ClassName", obj.getClassName());
        assertEquals("PlayFireEffects", obj.getPlayFireEffects());
        assertEquals("AmmoFiredDelegate", obj.getAmmoFiredDelegate());
        assertEquals("FiringTransform", obj.getFiringTransform());
        assertEquals("FiringDirection", obj.getFiringDirection());
        assertEquals("MagazineSize", obj.getMagazineSize());
        assertEquals("MaxAmmoEffectiveRange", obj.getMaxAmmoEffectiveRange());
        assertEquals("ReloadTimeMultiplier", obj.getReloadTimeMultiplier());
        assertEquals("FireRate", obj.getFireRate());
        assertEquals("FiringTransformIgnoresDispersion", obj.getFiringTransformIgnoresDispersion());
        assertEquals("DispersionFireRateMultiplier", obj.getDispersionFireRateMultiplier());
        assertEquals("DispersionPerShot", obj.getDispersionPerShot());
        assertEquals("RestingDispersion", obj.getRestingDispersion());
        assertEquals("FiringDispersion", obj.getFiringDispersion());
        assertEquals("DispersionRecoveryTime", obj.getDispersionRecoveryTime());
        assertEquals("HasBeenInitialized", obj.getHasBeenInitialized());
        assertEquals("WeaponDamageMultiplier", obj.getWeaponDamageMultiplier());
        assertEquals("MagazineMeshMaterials", obj.getMagazineMeshMaterials());
        assertEquals("DamageTypesOnImpact", obj.getDamageTypesOnImpact());
        assertEquals("AmmoDamageFalloff", obj.getAmmoDamageFalloff());
        assertEquals("MuzzleFlashScale", obj.getMuzzleFlashScale());
        assertEquals("FiringSounds", obj.getFiringSounds());
        assertEquals("AmmoColor", obj.getAmmoColor());
        assertEquals("AmmoScale", obj.getAmmoScale());
        assertEquals("AmmoTickFunction", obj.getAmmoTickFunction());
        assertEquals("DisplayName", obj.getDisplayName());
        assertEquals("Description", obj.getDescription());
        assertEquals("AbbreviatedDisplayName", obj.getAbbreviatedDisplayName());
        assertEquals("StackSize", obj.getStackSize());
        assertEquals("CanBeDiscarded", obj.getCanBeDiscarded());
        assertEquals("RememberPickUp", obj.getRememberPickUp());
        assertEquals("EnergyValue", obj.getEnergyValue());
        assertEquals("RadioactiveDecay", obj.getRadioactiveDecay());
        assertEquals("Form", obj.getForm());
        assertEquals("SmallIcon", obj.getSmallIcon());
        assertEquals("PersistentBigIcon", obj.getPersistentBigIcon());
        assertEquals("CrosshairMaterial", obj.getCrosshairMaterial());
        assertEquals("DescriptorStatBars", obj.getDescriptorStatBars());
        assertEquals("SubCategories", obj.getSubCategories());
        assertEquals("MenuPriority", obj.getMenuPriority());
        assertEquals("FluidColor", obj.getFluidColor());
        assertEquals("GasColor", obj.getGasColor());
        assertEquals("CompatibleItemDescriptors", obj.getCompatibleItemDescriptors());
        assertEquals("ClassToScanFor", obj.getClassToScanFor());
        assertEquals("ScannableType", obj.getScannableType());
        assertEquals("ShouldOverrideScannerDisplayText", obj.getShouldOverrideScannerDisplayText());
        assertEquals("ScannerDisplayText", obj.getScannerDisplayText());
        assertEquals("ScannerLightColor", obj.getScannerLightColor());
        assertEquals("ResourceSinkPoints", obj.getResourceSinkPoints());
    }

    private SatisfactoryClass.Builder getBuilderWithAllPropertiesFilled() {
        return SatisfactoryClass.newBuilder()
                .withClassName("ClassName")
                .withPlayFireEffects("PlayFireEffects")
                .withAmmoFiredDelegate("AmmoFiredDelegate")
                .withFiringTransform("FiringTransform")
                .withFiringDirection("FiringDirection")
                .withMagazineSize("MagazineSize")
                .withMaxAmmoEffectiveRange("MaxAmmoEffectiveRange")
                .withReloadTimeMultiplier("ReloadTimeMultiplier")
                .withFireRate("FireRate")
                .withFiringTransformIgnoresDispersion("FiringTransformIgnoresDispersion")
                .withDispersionFireRateMultiplier("DispersionFireRateMultiplier")
                .withDispersionPerShot("DispersionPerShot")
                .withRestingDispersion("RestingDispersion")
                .withFiringDispersion("FiringDispersion")
                .withDispersionRecoveryTime("DispersionRecoveryTime")
                .withHasBeenInitialized("HasBeenInitialized")
                .withWeaponDamageMultiplier("WeaponDamageMultiplier")
                .withMagazineMeshMaterials("MagazineMeshMaterials")
                .withDamageTypesOnImpact("DamageTypesOnImpact")
                .withAmmoDamageFalloff("AmmoDamageFalloff")
                .withMuzzleFlashScale("MuzzleFlashScale")
                .withFiringSounds("FiringSounds")
                .withAmmoColor("AmmoColor")
                .withAmmoScale("AmmoScale")
                .withAmmoTickFunction("AmmoTickFunction")
                .withDisplayName("DisplayName")
                .withDescription("Description")
                .withAbbreviatedDisplayName("AbbreviatedDisplayName")
                .withStackSize("StackSize")
                .withCanBeDiscarded("CanBeDiscarded")
                .withRememberPickUp("RememberPickUp")
                .withEnergyValue("EnergyValue")
                .withRadioactiveDecay("RadioactiveDecay")
                .withForm("Form")
                .withSmallIcon("SmallIcon")
                .withPersistentBigIcon("PersistentBigIcon")
                .withCrosshairMaterial("CrosshairMaterial")
                .withDescriptorStatBars("DescriptorStatBars")
                .withSubCategories("SubCategories")
                .withMenuPriority("MenuPriority")
                .withFluidColor("FluidColor")
                .withGasColor("GasColor")
                .withCompatibleItemDescriptors("CompatibleItemDescriptors")
                .withClassToScanFor("ClassToScanFor")
                .withScannableType("ScannableType")
                .withShouldOverrideScannerDisplayText("ShouldOverrideScannerDisplayText")
                .withScannerDisplayText("ScannerDisplayText")
                .withScannerLightColor("ScannerLightColor")
                .withResourceSinkPoints("ResourceSinkPoints");
    }
}
