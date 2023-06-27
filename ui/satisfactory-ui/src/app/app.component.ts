import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import * as cytoscape from 'cytoscape';
import * as klay from 'cytoscape-klay';
import { exampleData } from './example.data';

interface Data {
    name: string;
    children: Array<Data>;
}

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
    private canvas!: cytoscape.Core;

    @ViewChild('chart', { static: true }) myChart!: ElementRef;

    ngAfterViewInit(): void {
        cytoscape.use(klay);
        this.canvas = cytoscape({
            container: this.myChart.nativeElement,

            boxSelectionEnabled: false,
            autounselectify: true,
            style: [
                {
                    selector: 'node',
                    style: {
                        'content': 'data(id)',
                        'width': '150px',
                        'height': '100px',
                        'shape': 'rectangle',
                        'background-opacity': 0,
                        'border-color': 'green',
                        'border-width': 5,
                        "text-margin-y": -10
                    }
                },
                {
                    selector: 'edge',
                    style: {
                        'curve-style': 'bezier',
                        'target-arrow-shape': 'triangle',
                        'width': 4,
                        'line-color': '#ddd',
                        'target-arrow-color': '#ddd',
                        'content': 'data(amount)',
                        'text-margin-y': -10
                    }
                },
                {
                    selector: '.highlighted',
                    style: {
                        'background-color': '#61bffc',
                        'line-color': '#61bffc',
                        'target-arrow-color': '#61bffc',
                        'transition-property': 'background-color, line-color, target-arrow-color',
                        'transition-duration': 0.5
                    }
                }
            ],

            elements: this.getData(),

            layout: <klay.KlayLayoutOptions>{
                name: 'klay',
                fit: true,
                padding: 0,
                spacingFactor: 3,
                klay: {
                    nodeLayering: 'INTERACTIVE',
                    randomizationSeed: 2
                }
            }
        });
    }

    private getData(): cytoscape.ElementsDefinition {
        return {
            nodes: this.getNodes(),
            edges: this.getEdges()
        };
    }

    private getEdges(): Array<cytoscape.EdgeDefinition> {
        return this.getFlatten()
            .filter(item => item.parent)
            .map(item => ({
                data: {
                    id: `${item.name}-${item.parent}`,
                    source: item.name,
                    target: item.parent as string,
                    amount: item.amount
                }
            }));
    }

    private getNodes(): Array<cytoscape.NodeDefinition> {
        return this.getFlatten().map(item => ({ data: { id: item.name } }));
    }

    private getFlatten(): Array<{ name: string, parent: string | undefined, amount: number }> {
        const result: Array<{ name: string, parent: string | undefined, amount: number }> = [
            { name: 'Result', parent: undefined, amount: exampleData.amount },
            { name: exampleData.recipeClassName, parent: 'Result', amount: exampleData.amount }
        ];
        result.push(...this.getFlat(exampleData.preSteps, exampleData.recipeClassName));
        return result;
    }

    private getFlat(preSteps: Array<any>, parent?: string): Array<{ name: string, parent: string | undefined, amount: number }> {
        return preSteps.reduce((prev, curr) => {
            prev.push({ name: curr.recipeClassName, parent: parent, amount: curr.amount });
            if (curr.preSteps) {
                prev.push(...this.getFlat(curr.preSteps, curr.recipeClassName));
            }
            return prev;
        }, []);
    }
}
