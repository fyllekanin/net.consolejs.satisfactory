
export interface IFlowChartNode extends cytoscape.NodeDefinition {
    data: {
        id: string;
        name: string;
        backgroundImage?: string;
    }
}

export interface IFlowChartEdge extends cytoscape.EdgeDefinition {
    data: {
        id: string;
        source: string;
        target: string;
        text?: string;
    }
}

export interface IFlowChart {
    nodes: Array<IFlowChartNode>;
    edges: Array<IFlowChartEdge>;
}
