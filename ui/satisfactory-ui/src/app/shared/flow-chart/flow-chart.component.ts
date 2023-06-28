import { AfterViewInit, Component, ElementRef, HostBinding, Input, ViewChild } from '@angular/core';
import { IFlowChart } from './flow-chart.interface';
import * as cytoscape from 'cytoscape';
import * as klay from 'cytoscape-klay';

@Component({
    selector: 'app-shared-flow-chart',
    templateUrl: './flow-chart.component.html',
    styleUrls: ['./flow-chart.component.css'],
    standalone: true,
})
export class FlowChartComponent implements AfterViewInit {
    private isViewInitialized: boolean = false;
    private _data!: IFlowChart;

    @ViewChild('chart', { static: true }) myChart!: ElementRef;

    @Input()
    set data(data: IFlowChart) {
        this._data = data;
        if (this.isViewInitialized) {
            this.generate();
        }
    }

    @Input()
    @HostBinding('style.height.px')
    height: number = 700;

    ngAfterViewInit(): void {
        cytoscape.use(klay);
        this.generate();
        this.isViewInitialized = true;
    }

    private generate(): void {
        cytoscape({
            container: this.myChart.nativeElement,

            boxSelectionEnabled: false,
            autounselectify: true,
            wheelSensitivity: 0.1,
            style: this.getStyle(),
            layout: <klay.KlayLayoutOptions>{
                name: 'klay',
                fit: true,
                padding: 15,
                spacingFactor: 2,
                klay: {
                    nodeLayering: 'INTERACTIVE',
                    randomizationSeed: 2
                }
            },
            elements: {
                nodes: this._data.nodes,
                edges: this._data.edges
            }
        });
    }

    private getStyle(): Array<cytoscape.Stylesheet> {
        return [
            {
                selector: 'node',
                style: {
                    'content': 'data(name)',
                    'width': '150px',
                    'height': '100px',
                    'shape': 'rectangle',
                    'text-margin-y': -10,
                    'background-image': 'data(backgroundImage)',
                    'background-fit': 'contain',
                    'background-opacity': 0,
                    'color': 'white'
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
                    'content': 'data(text)',
                    'text-margin-y': -10,
                    'color': 'white'
                }
            }
        ];
    }
}
