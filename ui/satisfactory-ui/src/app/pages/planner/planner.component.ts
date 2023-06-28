import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { PlannerStep } from './planner.model';
import { IFlowChart } from 'src/app/shared/flow-chart/flow-chart.interface';
import { AppService } from 'src/app/core/app/app.service';

interface FlattenData {
    name: string;
    parent?: string;
    amount: number;
    icon?: string;
    amountMachines?: number;
}

@Component({
    selector: 'app-page-planner',
    templateUrl: './planner.component.html'
})
export class PlannerComponent implements OnInit, OnDestroy {
    private appService: AppService;

    private activatedRouter!: ActivatedRoute;
    private onDataSubscription!: Subscription;
    private data!: PlannerStep;

    flowChart!: IFlowChart;

    constructor() {
        this.activatedRouter = inject(ActivatedRoute);
        this.appService = inject(AppService);
    }

    ngOnInit(): void {
        this.onDataSubscription = this.activatedRouter.data.subscribe(data => {
            this.data = data['solution'];
            this.flowChart = this.data ? this.getFlowChart() : {
                nodes: [],
                edges: []
            };
        });
    }

    ngOnDestroy(): void {
        this.onDataSubscription.unsubscribe();
    }

    private getFlowChart(): IFlowChart {
        const flattenData: Array<FlattenData> = this.getFlattenData();

        return {
            nodes: flattenData.map(item => ({
                data: {
                    id: item.name,
                    name: `${item.name}${item.amountMachines ? ' - x' + item.amountMachines : ''}`,
                    backgroundImage: item.icon
                }
            })),
            edges: flattenData
                .filter(item => item.parent)
                .map(item => ({
                    data: {
                        id: `${item.name}-${item.parent}`,
                        source: item.name,
                        target: `${item.parent}`,
                        text: `${item.amount}`
                    }
                }))
        }
    }

    private getFlattenData(): Array<FlattenData> {
        const result: Array<FlattenData> = [
            { name: 'Result', amount: this.data.amount, icon: `/resources/${this.appService.getGameVersion()}${this.data.icon}.png` },
            { name: this.data.displayName, parent: 'Result', amount: this.data.amount, icon: this.getIconUrl(this.data), amountMachines: this.data.manufacturer?.amount }
        ];

        return [...result, ...this.getFlat(this.data.preSteps, this.data.displayName)];
    }

    private getFlat(items: Array<PlannerStep>, parent?: string): Array<FlattenData> {
        return items.reduce((prev, curr) => {
            prev.push({
                name: curr.displayName,
                parent: parent,
                amount: curr.amount,
                icon: this.getIconUrl(curr),
                amountMachines: curr.manufacturer?.amount
            });
            if (curr.preSteps) {
                prev.push(...this.getFlat(curr.preSteps, curr.displayName));
            }
            return prev;
        }, [] as Array<FlattenData>);
    }

    private getIconUrl(data: PlannerStep): string | undefined {
        if (data.manufacturer) {
            return `/resources/${this.appService.getGameVersion()}${data.manufacturer.icon}.png`;
        }
        if (data.extractor) {
            return `/resources/${this.appService.getGameVersion()}${data.extractor.icon}.png`;
        }
        return undefined;
    }
}
