import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PlannerComponent } from './planner.component';
import { PlannerService, itemsResolver, plannerResolver } from './planner.service';
import { HttpClientModule } from '@angular/common/http';
import { AsyncPipe, CommonModule } from '@angular/common';
import { FlowChartComponent } from 'src/app/shared/flow-chart/flow-chart.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { PlanerDrawerComponent } from './drawer/planner-drawer.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@NgModule({
    imports: [
        HttpClientModule,
        CommonModule,
        FlowChartComponent,
        MatSidenavModule,
        MatFormFieldModule,
        FormsModule,
        MatAutocompleteModule,
        MatInputModule,
        ReactiveFormsModule,
        AsyncPipe,
        RouterModule.forChild([
            {
                path: '',
                pathMatch: 'full',
                component: PlannerComponent,
                resolve: {
                    solution: plannerResolver,
                    items: itemsResolver
                },
                runGuardsAndResolvers: 'paramsOrQueryParamsChange'
            }
        ])
    ],
    declarations: [
        PlannerComponent,
        PlanerDrawerComponent
    ],
    providers: [
        PlannerService
    ],
    exports: [
        RouterModule
    ]
})
export class PlannerModule { }
