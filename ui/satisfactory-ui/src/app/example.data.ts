export const exampleData = {
    "recipeClassName": "Recipe_Computer_C",
    "amount": 15.0,
    "manufacturer": {
        "manufacturerClassName": "Build_ManufacturerMk1_C",
        "icon": "/Game/FactoryGame/Buildable/Factory/ManufacturerMk1/UI/IconDesc_Manufacturer_256.IconDesc_Manufacturer_256",
        "displayName": "Manufacturer"
    },
    "preSteps": [
        {
            "recipeClassName": "Recipe_CircuitBoard_C",
            "amount": 150.0,
            "manufacturer": {
                "manufacturerClassName": "Build_AssemblerMk1_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/AssemblerMk1/UI/IconDesc_AssemblerMk1_256.IconDesc_AssemblerMk1_256",
                "displayName": "Assembler"
            },
            "preSteps": [
                {
                    "recipeClassName": "Recipe_CopperSheet_C",
                    "amount": 300.0,
                    "manufacturer": {
                        "manufacturerClassName": "Build_ConstructorMk1_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256.IconDesc_ConstructorMk1_256",
                        "displayName": "Constructor"
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Recipe_IngotCopper_C",
                            "amount": 600.0,
                            "manufacturer": {
                                "manufacturerClassName": "Build_SmelterMk1_C",
                                "icon": "/Game/FactoryGame/Buildable/Factory/SmelterMk1/UI/IconDesc_SmelterMk1_256.IconDesc_SmelterMk1_256",
                                "displayName": "Smelter"
                            },
                            "preSteps": [
                                {
                                    "recipeClassName": "Desc_OreCopper_C",
                                    "amount": 600.0
                                }
                            ]
                        }
                    ]
                },
                {
                    "recipeClassName": "Recipe_Plastic_C",
                    "amount": 600.0,
                    "manufacturer": {
                        "manufacturerClassName": "Build_OilRefinery_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/OilRefinery/UI/IconDesc_OilRefinery_256.IconDesc_OilRefinery_256",
                        "displayName": "Refinery"
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Desc_LiquidOil_C",
                            "amount": 900.0
                        }
                    ]
                }
            ]
        },
        {
            "recipeClassName": "Recipe_Cable_C",
            "amount": 135.0,
            "manufacturer": {
                "manufacturerClassName": "Build_ConstructorMk1_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256.IconDesc_ConstructorMk1_256",
                "displayName": "Constructor"
            },
            "preSteps": [
                {
                    "recipeClassName": "Recipe_Wire_C",
                    "amount": 270.0,
                    "manufacturer": {
                        "manufacturerClassName": "Build_ConstructorMk1_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256.IconDesc_ConstructorMk1_256",
                        "displayName": "Constructor"
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Recipe_IngotCopper_C",
                            "amount": 135.0,
                            "manufacturer": {
                                "manufacturerClassName": "Build_SmelterMk1_C",
                                "icon": "/Game/FactoryGame/Buildable/Factory/SmelterMk1/UI/IconDesc_SmelterMk1_256.IconDesc_SmelterMk1_256",
                                "displayName": "Smelter"
                            },
                            "preSteps": [
                                {
                                    "recipeClassName": "Desc_OreCopper_C",
                                    "amount": 135.0
                                }
                            ]
                        }
                    ]
                }
            ]
        },
        {
            "recipeClassName": "Recipe_Plastic_C",
            "amount": 270.0,
            "manufacturer": {
                "manufacturerClassName": "Build_OilRefinery_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/OilRefinery/UI/IconDesc_OilRefinery_256.IconDesc_OilRefinery_256",
                "displayName": "Refinery"
            },
            "preSteps": [
                {
                    "recipeClassName": "Desc_LiquidOil_C",
                    "amount": 405.0
                }
            ]
        },
        {
            "recipeClassName": "Recipe_Screw_C",
            "amount": 780.0,
            "manufacturer": {
                "manufacturerClassName": "Build_ConstructorMk1_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256.IconDesc_ConstructorMk1_256",
                "displayName": "Constructor"
            },
            "preSteps": [
                {
                    "recipeClassName": "Recipe_IronRod_C",
                    "amount": 195.0,
                    "manufacturer": {
                        "manufacturerClassName": "Build_ConstructorMk1_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256.IconDesc_ConstructorMk1_256",
                        "displayName": "Constructor"
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Recipe_IngotIron_C",
                            "amount": 195.0,
                            "manufacturer": {
                                "manufacturerClassName": "Build_SmelterMk1_C",
                                "icon": "/Game/FactoryGame/Buildable/Factory/SmelterMk1/UI/IconDesc_SmelterMk1_256.IconDesc_SmelterMk1_256",
                                "displayName": "Smelter"
                            },
                            "preSteps": [
                                {
                                    "recipeClassName": "Desc_OreIron_C",
                                    "amount": 195.0
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]
};
