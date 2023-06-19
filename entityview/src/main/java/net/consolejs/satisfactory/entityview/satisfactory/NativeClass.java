package net.consolejs.satisfactory.entityview.satisfactory;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum NativeClass {


    @SerializedName("Class'/Script/FactoryGame.FGItemDescriptor'")
    FGItemDescriptor("Class'/Script/FactoryGame.FGItemDescriptor'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableDroneStation'")
    FGBuildableDroneStation("Class'/Script/FactoryGame.FGBuildableDroneStation'"),
    @SerializedName("Class'/Script/FactoryGame.FGConsumableEquipment'")
    FGConsumableEquipment("Class'/Script/FactoryGame.FGConsumableEquipment'"),
    @SerializedName("Class'/Script/FactoryGame.FGSchematic'")
    FGSchematic("Class'/Script/FactoryGame.FGSchematic'"),
    @SerializedName("Class'/Script/FactoryGame.FGConsumableDescriptor'")
    FGConsumableDescriptor("Class'/Script/FactoryGame.FGConsumableDescriptor'"),
    @SerializedName("Class'/Script/FactoryGame.FGItemDescriptorBiomass'")
    FGItemDescriptorBiomass("Class'/Script/FactoryGame.FGItemDescriptorBiomass'"),
    @SerializedName("Class'/Script/FactoryGame.FGResourceDescriptor'")
    FGResourceDescriptor("Class'/Script/FactoryGame.FGResourceDescriptor'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableWallLightweight'")
    FGBuildableWallLightweight("Class'/Script/FactoryGame.FGBuildableWallLightweight'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableWall'")
    FGBuildableWall("Class'/Script/FactoryGame.FGBuildableWall'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableDoor'")
    FGBuildableDoor("Class'/Script/FactoryGame.FGBuildableDoor'"),
    @SerializedName("Class'/Script/FactoryGame.FGRecipe'")
    FGRecipe("Class'/Script/FactoryGame.FGRecipe'"),
    @SerializedName("Class'/Script/FactoryGame.FGCustomizationRecipe'")
    FGCustomizationRecipe("Class'/Script/FactoryGame.FGCustomizationRecipe'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildingDescriptor'")
    FGBuildingDescriptor("Class'/Script/FactoryGame.FGBuildingDescriptor'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableCornerWall'")
    FGBuildableCornerWall("Class'/Script/FactoryGame.FGBuildableCornerWall'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableRailroadTrack'")
    FGBuildableRailroadTrack("Class'/Script/FactoryGame.FGBuildableRailroadTrack'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableBlueprintDesigner'")
    FGBuildableBlueprintDesigner("Class'/Script/FactoryGame.FGBuildableBlueprintDesigner'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFrackingExtractor'")
    FGBuildableFrackingExtractor("Class'/Script/FactoryGame.FGBuildableFrackingExtractor'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFrackingActivator'")
    FGBuildableFrackingActivator("Class'/Script/FactoryGame.FGBuildableFrackingActivator'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableGeneratorGeoThermal'")
    FGBuildableGeneratorGeoThermal("Class'/Script/FactoryGame.FGBuildableGeneratorGeoThermal'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableResourceExtractor'")
    FGBuildableResourceExtractor("Class'/Script/FactoryGame.FGBuildableResourceExtractor'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableSpaceElevator'")
    FGBuildableSpaceElevator("Class'/Script/FactoryGame.FGBuildableSpaceElevator'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableTradingPost'")
    FGBuildableTradingPost("Class'/Script/FactoryGame.FGBuildableTradingPost'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildable'")
    FGBuildable("Class'/Script/FactoryGame.FGBuildable'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePoleLightweight'")
    FGBuildablePoleLightweight("Class'/Script/FactoryGame.FGBuildablePoleLightweight'"),
    @SerializedName("Class'/Script/FactoryGame.FGPoleDescriptor'")
    FGPoleDescriptor("Class'/Script/FactoryGame.FGPoleDescriptor'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableConveyorBelt'")
    FGBuildableConveyorBelt("Class'/Script/FactoryGame.FGBuildableConveyorBelt'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableWire'")
    FGBuildableWire("Class'/Script/FactoryGame.FGBuildableWire'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePowerPole'")
    FGBuildablePowerPole("Class'/Script/FactoryGame.FGBuildablePowerPole'"),
    @SerializedName("Class'/Script/FactoryGame.FGChainsaw'")
    FGChainsaw("Class'/Script/FactoryGame.FGChainsaw'"),
    @SerializedName("Class'/Script/FactoryGame.FGEquipmentDescriptor'")
    FGEquipmentDescriptor("Class'/Script/FactoryGame.FGEquipmentDescriptor'"),
    @SerializedName("Class'/Script/FactoryGame.FGItemDescriptorNuclearFuel'")
    FGItemDescriptorNuclearFuel("Class'/Script/FactoryGame.FGItemDescriptorNuclearFuel'"),
    @SerializedName("Class'/Script/FactoryGame.FGGolfCartDispenser'")
    FGGolfCartDispenser("Class'/Script/FactoryGame.FGGolfCartDispenser'"),
    @SerializedName("Class'/Script/FactoryGame.FGSuitBase'")
    FGSuitBase("Class'/Script/FactoryGame.FGSuitBase'"),
    @SerializedName("Class'/Script/FactoryGame.FGJetPack'")
    FGJetPack("Class'/Script/FactoryGame.FGJetPack'"),
    @SerializedName("Class'/Script/FactoryGame.FGJumpingStilts'")
    FGJumpingStilts("Class'/Script/FactoryGame.FGJumpingStilts'"),
    @SerializedName("Class'/Script/FactoryGame.FGAmmoTypeProjectile'")
    FGAmmoTypeProjectile("Class'/Script/FactoryGame.FGAmmoTypeProjectile'"),
    @SerializedName("Class'/Script/FactoryGame.FGAmmoTypeSpreadshot'")
    FGAmmoTypeSpreadshot("Class'/Script/FactoryGame.FGAmmoTypeSpreadshot'"),
    @SerializedName("Class'/Script/FactoryGame.FGWeapon'")
    FGWeapon("Class'/Script/FactoryGame.FGWeapon'"),
    @SerializedName("Class'/Script/FactoryGame.FGAmmoTypeInstantHit'")
    FGAmmoTypeInstantHit("Class'/Script/FactoryGame.FGAmmoTypeInstantHit'"),
    @SerializedName("Class'/Script/FactoryGame.FGEquipmentStunSpear'")
    FGEquipmentStunSpear("Class'/Script/FactoryGame.FGEquipmentStunSpear'"),
    @SerializedName("Class'/Script/FactoryGame.FGChargedWeapon'")
    FGChargedWeapon("Class'/Script/FactoryGame.FGChargedWeapon'"),
    @SerializedName("Class'/Script/FactoryGame.FGGasMask'")
    FGGasMask("Class'/Script/FactoryGame.FGGasMask'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableWaterPump'")
    FGBuildableWaterPump("Class'/Script/FactoryGame.FGBuildableWaterPump'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableManufacturer'")
    FGBuildableManufacturer("Class'/Script/FactoryGame.FGBuildableManufacturer'"),
    @SerializedName("Class'/Script/FactoryGame.FGPortableMinerDispenser'")
    FGPortableMinerDispenser("Class'/Script/FactoryGame.FGPortableMinerDispenser'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableStorage'")
    FGBuildableStorage("Class'/Script/FactoryGame.FGBuildableStorage'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableGeneratorFuel'")
    FGBuildableGeneratorFuel("Class'/Script/FactoryGame.FGBuildableGeneratorFuel'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableMAM'")
    FGBuildableMAM("Class'/Script/FactoryGame.FGBuildableMAM'"),
    @SerializedName("Class'/Script/FactoryGame.FGObjectScanner'")
    FGObjectScanner("Class'/Script/FactoryGame.FGObjectScanner'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableBeamLightweight'")
    FGBuildableBeamLightweight("Class'/Script/FactoryGame.FGBuildableBeamLightweight'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePillarLightweight'")
    FGBuildablePillarLightweight("Class'/Script/FactoryGame.FGBuildablePillarLightweight'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFactory'")
    FGBuildableFactory("Class'/Script/FactoryGame.FGBuildableFactory'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableWalkwayLightweight'")
    FGBuildableWalkwayLightweight("Class'/Script/FactoryGame.FGBuildableWalkwayLightweight'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableWalkway'")
    FGBuildableWalkway("Class'/Script/FactoryGame.FGBuildableWalkway'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePipelinePump'")
    FGBuildablePipelinePump("Class'/Script/FactoryGame.FGBuildablePipelinePump'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePipelineSupport'")
    FGBuildablePipelineSupport("Class'/Script/FactoryGame.FGBuildablePipelineSupport'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePipeline'")
    FGBuildablePipeline("Class'/Script/FactoryGame.FGBuildablePipeline'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePipelineJunction'")
    FGBuildablePipelineJunction("Class'/Script/FactoryGame.FGBuildablePipelineJunction'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePipeReservoir'")
    FGBuildablePipeReservoir("Class'/Script/FactoryGame.FGBuildablePipeReservoir'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableResourceSink'")
    FGBuildableResourceSink("Class'/Script/FactoryGame.FGBuildableResourceSink'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableResourceSinkShop'")
    FGBuildableResourceSinkShop("Class'/Script/FactoryGame.FGBuildableResourceSinkShop'"),
    @SerializedName("Class'/Script/FactoryGame.FGVehicleDescriptor'")
    FGVehicleDescriptor("Class'/Script/FactoryGame.FGVehicleDescriptor'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableManufacturerVariablePower'")
    FGBuildableManufacturerVariablePower("Class'/Script/FactoryGame.FGBuildableManufacturerVariablePower'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableGeneratorNuclear'")
    FGBuildableGeneratorNuclear("Class'/Script/FactoryGame.FGBuildableGeneratorNuclear'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableConveyorLift'")
    FGBuildableConveyorLift("Class'/Script/FactoryGame.FGBuildableConveyorLift'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFoundation'")
    FGBuildableFoundation("Class'/Script/FactoryGame.FGBuildableFoundation'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFoundationLightweight'")
    FGBuildableFoundationLightweight("Class'/Script/FactoryGame.FGBuildableFoundationLightweight'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableRamp'")
    FGBuildableRamp("Class'/Script/FactoryGame.FGBuildableRamp'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableSplitterSmart'")
    FGBuildableSplitterSmart("Class'/Script/FactoryGame.FGBuildableSplitterSmart'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableAttachmentMerger'")
    FGBuildableAttachmentMerger("Class'/Script/FactoryGame.FGBuildableAttachmentMerger'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableAttachmentSplitter'")
    FGBuildableAttachmentSplitter("Class'/Script/FactoryGame.FGBuildableAttachmentSplitter'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableJumppad'")
    FGBuildableJumppad("Class'/Script/FactoryGame.FGBuildableJumppad'"),
    @SerializedName("Class'/Script/FactoryGame.FGConveyorPoleStackable'")
    FGConveyorPoleStackable("Class'/Script/FactoryGame.FGConveyorPoleStackable'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableDockingStation'")
    FGBuildableDockingStation("Class'/Script/FactoryGame.FGBuildableDockingStation'"),
    @SerializedName("Class'/Script/FactoryGame.FGPipeHyperStart'")
    FGPipeHyperStart("Class'/Script/FactoryGame.FGPipeHyperStart'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePipeHyper'")
    FGBuildablePipeHyper("Class'/Script/FactoryGame.FGBuildablePipeHyper'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePowerStorage'")
    FGBuildablePowerStorage("Class'/Script/FactoryGame.FGBuildablePowerStorage'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableRailroadSignal'")
    FGBuildableRailroadSignal("Class'/Script/FactoryGame.FGBuildableRailroadSignal'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableTrainPlatformCargo'")
    FGBuildableTrainPlatformCargo("Class'/Script/FactoryGame.FGBuildableTrainPlatformCargo'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableTrainPlatformEmpty'")
    FGBuildableTrainPlatformEmpty("Class'/Script/FactoryGame.FGBuildableTrainPlatformEmpty'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableRailroadStation'")
    FGBuildableRailroadStation("Class'/Script/FactoryGame.FGBuildableRailroadStation'"),
    @SerializedName("Class'/Script/FactoryGame.FGHoverPack'")
    FGHoverPack("Class'/Script/FactoryGame.FGHoverPack'"),
    @SerializedName("Class'/Script/FactoryGame.FGEquipmentZipline'")
    FGEquipmentZipline("Class'/Script/FactoryGame.FGEquipmentZipline'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableCircuitSwitch'")
    FGBuildableCircuitSwitch("Class'/Script/FactoryGame.FGBuildableCircuitSwitch'"),
    @SerializedName("Class'/Script/FactoryGame.FGParachute'")
    FGParachute("Class'/Script/FactoryGame.FGParachute'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableRadarTower'")
    FGBuildableRadarTower("Class'/Script/FactoryGame.FGBuildableRadarTower'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFactorySimpleProducer'")
    FGBuildableFactorySimpleProducer("Class'/Script/FactoryGame.FGBuildableFactorySimpleProducer'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableSnowDispenser'")
    FGBuildableSnowDispenser("Class'/Script/FactoryGame.FGBuildableSnowDispenser'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFactoryBuilding'")
    FGBuildableFactoryBuilding("Class'/Script/FactoryGame.FGBuildableFactoryBuilding'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableWidgetSign'")
    FGBuildableWidgetSign("Class'/Script/FactoryGame.FGBuildableWidgetSign'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableLightSource'")
    FGBuildableLightSource("Class'/Script/FactoryGame.FGBuildableLightSource'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableLadder'")
    FGBuildableLadder("Class'/Script/FactoryGame.FGBuildableLadder'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableStair'")
    FGBuildableStair("Class'/Script/FactoryGame.FGBuildableStair'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableFloodlight'")
    FGBuildableFloodlight("Class'/Script/FactoryGame.FGBuildableFloodlight'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildableLightsControlPanel'")
    FGBuildableLightsControlPanel("Class'/Script/FactoryGame.FGBuildableLightsControlPanel'"),
    @SerializedName("Class'/Script/FactoryGame.FGBuildablePassthrough'")
    FGBuildablePassthrough("Class'/Script/FactoryGame.FGBuildablePassthrough'");

    private final String key;

    private static final Map<String, NativeClass> ENUM_MAP;

    NativeClass(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    static {
        Map<String, NativeClass> map = new ConcurrentHashMap<String, NativeClass>();
        for (NativeClass instance : NativeClass.values()) {
            map.put(instance.getKey().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static NativeClass get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
