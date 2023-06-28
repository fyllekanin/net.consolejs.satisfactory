import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import * as cytoscape from 'cytoscape';
import * as klay from 'cytoscape-klay';
import { exampleData } from './example.data';

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
            wheelSensitivity: 0.1,
            style: [
                {
                    selector: 'node',
                    style: {
                        'content': 'data(name)',
                        'width': '150px',
                        'height': '100px',
                        'shape': 'rectangle',
                        'text-margin-y': -10,
                        'background-image': 'data(icon)',
                        'background-fit': 'contain',
                        'background-opacity': 0
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
                spacingFactor: 2,
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
        return this.getFlatten().map(item => ({
            data: {
                id: item.name,
                name: `${item.name}${item.amountMachines ? ' - x' + item.amountMachines : ''}`,
                icon: item.icon
            }
        }));
    }

    private getFlatten(): Array<{ name: string, parent: string | undefined, amount: number, icon?: string, amountMachines?: number }> {
        const result: Array<{ name: string, parent: string | undefined, amount: number, icon?: string, amountMachines?: number }> = [
            { name: 'Result', parent: undefined, amount: exampleData.amount, icon: this.getIconUrl(exampleData.icon) },
            { name: exampleData.displayName, parent: 'Result', amount: exampleData.amount, icon: this.getIconUrl(exampleData.manufacturer.icon), amountMachines: exampleData.manufacturer?.amount }
        ];
        result.push(...this.getFlat(exampleData.preSteps, exampleData.displayName));
        return result;
    }

    private getFlat(preSteps: Array<any>, parent?: string): Array<{ name: string, parent: string | undefined, amount: number, icon: string, amountMachines: number }> {
        return preSteps.reduce((prev, curr) => {
            prev.push({
                name: curr.displayName,
                parent: parent,
                amount: curr.amount,
                icon: this.getIconUrl(curr.manufacturer?.icon) || this.getIconUrl(curr.extractor?.icon),
                amountMachines: curr.manufacturer?.amount
            });
            if (curr.preSteps) {
                prev.push(...this.getFlat(curr.preSteps, curr.displayName));
            }
            return prev;
        }, []);
    }

    private getIconUrl(icon: string): string | undefined {
        return icon ? `/resources/test1${icon}.png` : undefined;
    }
}
