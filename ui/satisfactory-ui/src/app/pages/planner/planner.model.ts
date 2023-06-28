
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
