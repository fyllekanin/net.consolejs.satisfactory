import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PlannerComponent } from './planner.component';
import { PlannerService, plannerResolver, recipeResolver } from './planner.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FlowChartComponent } from 'src/app/shared/flow-chart/flow-chart.component';

@NgModule({
    imports: [
        HttpClientModule,
        CommonModule,
        FlowChartComponent,
        RouterModule.forChild([
            {
                path: '',
                pathMatch: 'full',
                component: PlannerComponent,
                resolve: {
                    solution: plannerResolver,
                    recipes: recipeResolver
                },
                runGuardsAndResolvers: 'paramsOrQueryParamsChange'
            }
        ])
    ],
    declarations: [
        PlannerComponent
    ],
    providers: [
        PlannerService
    ],
    exports: [
        RouterModule
    ]
})
export class PlannerModule { }
