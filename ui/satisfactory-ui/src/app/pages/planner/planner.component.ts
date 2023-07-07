import { Component, HostListener, OnDestroy, OnInit, ViewEncapsulation, inject } from '@angular/core';
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
    templateUrl: './planner.component.html',
    styleUrls: ['./planner.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class PlannerComponent implements OnInit, OnDestroy {
    private appService: AppService;

    private activatedRouter!: ActivatedRoute;
    private onDataSubscription!: Subscription;
    private data!: PlannerStep;

    private onResizeTimeout: any;

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

    @HostListener('window:resize', [])
    onResize(): void {
        if (this.onResizeTimeout) {
            clearTimeout(this.onResizeTimeout);
        }
        this.onResizeTimeout = setTimeout(() => {
            this.flowChart = this.data ? this.getFlowChart() : {
                nodes: [],
                edges: []
            };
        }, 100);
    }

    private getFlowChart(): IFlowChart {
        const flattenData: Array<FlattenData> = this.getFlattenData();

        return {
            nodes: flattenData.map(item => ({
                data: {
                    id: item.name,
                    name: `${item.name}${item.amountMachines ? ' - x' + this.getRoundedNumber(item.amountMachines) : ''}`,
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
                        text: `${this.getRoundedNumber(item.amount)}`
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

    private getRoundedNumber(amount: number): number {
        if (amount >= 1 && amount % 1 === 0) {
            return amount;
        } else if (amount < 1 && amount > 0 && String(amount).split('.')[1].length === 1) {
            return amount;
        }

        const value = (Math.round(amount * 10) / 10) + 0.1;
        const stringValue = String(value);
        const parts = stringValue.split('.');
        if (parts.length > 1 && parts[1].length > 1) {
            return Number(`${parts[0]}.${parts[1].slice(0, 1)}`);
        }
        return value;
    }
}
