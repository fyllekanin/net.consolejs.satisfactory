
export interface ItemRecipe {
    className: string;
    displayName: string;
    isAlternate: boolean;
}

export interface PlannerItem {
    className: string;
    displayName: string;
    recipes: Array<ItemRecipe>;
}

export interface PlannerExtractor {
    manufacturerClassName: string;
    icon: string;
    displayName: string;
}

export interface PlannerManufacturer {
    manufacturerClassName: string;
    icon: string;
    displayName: string;
    amount: number;
}

export interface PlannerStep {
    recipeClassName: string;
    amount: number;
    displayName: string;
    manufacturer: PlannerManufacturer;
    extractor: PlannerExtractor;
    preSteps: Array<PlannerStep>;
    icon: string;
}
