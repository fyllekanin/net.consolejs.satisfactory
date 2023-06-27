export const exampleData = {
    "recipeClassName": "Recipe_Computer_C",
    "amount": 15.0,
    "displayName": "Computer",
    "manufacturer": {
        "manufacturerClassName": "Build_ManufacturerMk1_C",
        "icon": "/Game/FactoryGame/Buildable/Factory/ManufacturerMk1/UI/IconDesc_Manufacturer_256",
        "displayName": "Manufacturer",
        "amount": 6.0
    },
    "preSteps": [
        {
            "recipeClassName": "Recipe_CircuitBoard_C",
            "amount": 150.0,
            "displayName": "Circuit Board",
            "manufacturer": {
                "manufacturerClassName": "Build_AssemblerMk1_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/AssemblerMk1/UI/IconDesc_AssemblerMk1_256",
                "displayName": "Assembler",
                "amount": 20.0
            },
            "preSteps": [
                {
                    "recipeClassName": "Recipe_CopperSheet_C",
                    "amount": 300.0,
                    "displayName": "Copper Sheet",
                    "manufacturer": {
                        "manufacturerClassName": "Build_ConstructorMk1_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256",
                        "displayName": "Constructor",
                        "amount": 30.0
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Recipe_IngotCopper_C",
                            "amount": 600.0,
                            "displayName": "Copper Ingot",
                            "manufacturer": {
                                "manufacturerClassName": "Build_SmelterMk1_C",
                                "icon": "/Game/FactoryGame/Buildable/Factory/SmelterMk1/UI/IconDesc_SmelterMk1_256",
                                "displayName": "Smelter",
                                "amount": 20.0
                            },
                            "preSteps": [
                                {
                                    "recipeClassName": "Desc_OreCopper_C",
                                    "amount": 600.0,
                                    "displayName": "Copper Ore",
                                    "extractor": {
                                        "extractorClassName": "Build_MinerMk1_C",
                                        "icon": "/Game/FactoryGame/Buildable/Factory/MinerMK1/UI/IconDesc_MinerMk1_256",
                                        "displayName": "Miner Mk.1"
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    "recipeClassName": "Recipe_Plastic_C",
                    "amount": 600.0,
                    "displayName": "Plastic",
                    "manufacturer": {
                        "manufacturerClassName": "Build_OilRefinery_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/OilRefinery/UI/IconDesc_OilRefinery_256",
                        "displayName": "Refinery",
                        "amount": 30.0
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Desc_LiquidOil_C",
                            "amount": 900.0,
                            "displayName": "Crude Oil",
                            "extractor": {
                                "extractorClassName": "Build_OilPump_C",
                                "icon": "/Game/FactoryGame/Buildable/Factory/OilPump/UI/OilPump_256",
                                "displayName": "Oil Extractor"
                            }
                        }
                    ]
                }
            ]
        },
        {
            "recipeClassName": "Recipe_Cable_C",
            "amount": 135.0,
            "displayName": "Cable",
            "manufacturer": {
                "manufacturerClassName": "Build_ConstructorMk1_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256",
                "displayName": "Constructor",
                "amount": 4.5
            },
            "preSteps": [
                {
                    "recipeClassName": "Recipe_Wire_C",
                    "amount": 270.0,
                    "displayName": "Wire",
                    "manufacturer": {
                        "manufacturerClassName": "Build_ConstructorMk1_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256",
                        "displayName": "Constructor",
                        "amount": 9.0
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Recipe_IngotCopper_C",
                            "amount": 135.0,
                            "displayName": "Copper Ingot",
                            "manufacturer": {
                                "manufacturerClassName": "Build_SmelterMk1_C",
                                "icon": "/Game/FactoryGame/Buildable/Factory/SmelterMk1/UI/IconDesc_SmelterMk1_256",
                                "displayName": "Smelter",
                                "amount": 4.5
                            },
                            "preSteps": [
                                {
                                    "recipeClassName": "Desc_OreCopper_C",
                                    "amount": 135.0,
                                    "displayName": "Copper Ore",
                                    "extractor": {
                                        "extractorClassName": "Build_MinerMk1_C",
                                        "icon": "/Game/FactoryGame/Buildable/Factory/MinerMK1/UI/IconDesc_MinerMk1_256",
                                        "displayName": "Miner Mk.1"
                                    }
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
            "displayName": "Plastic",
            "manufacturer": {
                "manufacturerClassName": "Build_OilRefinery_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/OilRefinery/UI/IconDesc_OilRefinery_256",
                "displayName": "Refinery",
                "amount": 13.5
            },
            "preSteps": [
                {
                    "recipeClassName": "Desc_LiquidOil_C",
                    "amount": 405.0,
                    "displayName": "Crude Oil",
                    "extractor": {
                        "extractorClassName": "Build_OilPump_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/OilPump/UI/OilPump_256",
                        "displayName": "Oil Extractor"
                    }
                }
            ]
        },
        {
            "recipeClassName": "Recipe_Screw_C",
            "amount": 780.0,
            "displayName": "Screw",
            "manufacturer": {
                "manufacturerClassName": "Build_ConstructorMk1_C",
                "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256",
                "displayName": "Constructor",
                "amount": 19.5
            },
            "preSteps": [
                {
                    "recipeClassName": "Recipe_IronRod_C",
                    "amount": 195.0,
                    "displayName": "Iron Rod",
                    "manufacturer": {
                        "manufacturerClassName": "Build_ConstructorMk1_C",
                        "icon": "/Game/FactoryGame/Buildable/Factory/ConstructorMk1/UI/IconDesc_ConstructorMk1_256",
                        "displayName": "Constructor",
                        "amount": 13.0
                    },
                    "preSteps": [
                        {
                            "recipeClassName": "Recipe_IngotIron_C",
                            "amount": 195.0,
                            "displayName": "Iron Ingot",
                            "manufacturer": {
                                "manufacturerClassName": "Build_SmelterMk1_C",
                                "icon": "/Game/FactoryGame/Buildable/Factory/SmelterMk1/UI/IconDesc_SmelterMk1_256",
                                "displayName": "Smelter",
                                "amount": 6.5
                            },
                            "preSteps": [
                                {
                                    "recipeClassName": "Desc_OreIron_C",
                                    "amount": 195.0,
                                    "displayName": "Iron Ore",
                                    "extractor": {
                                        "extractorClassName": "Build_MinerMk1_C",
                                        "icon": "/Game/FactoryGame/Buildable/Factory/MinerMK1/UI/IconDesc_MinerMk1_256",
                                        "displayName": "Miner Mk.1"
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ],
    "icon": "/Game/FactoryGame/Resource/Parts/Computer/UI/IconDesc_Computer_256"
};

