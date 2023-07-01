import { ItemRecipe, PlannerItem } from './../planner.model';
import { Component, OnDestroy, inject } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { map, startWith } from 'rxjs/operators';
import { AppService } from 'src/app/core/app/app.service';

@Component({
    selector: 'app-planner-drawer',
    templateUrl: './planner-drawer.component.html'
})
export class PlanerDrawerComponent implements OnDestroy {
    private subscriptions: Array<Subscription> = [];
    private queryParams!: Params;

    private activatedRoute: ActivatedRoute;
    private router: Router;
    private appService: AppService;

    private recipes: Array<ItemRecipe> = [];
    private items: Array<PlannerItem> = [];

    myItemControl = new FormControl<PlannerItem | null>(null);
    myRecipeControl = new FormControl<ItemRecipe | null>(null);
    myAmountControl = new FormControl<number>(1);

    filteredRecipeOptions!: Observable<Array<ItemRecipe>>;
    filteredItemOptions!: Observable<Array<PlannerItem>>;

    constructor() {
        this.activatedRoute = inject(ActivatedRoute);
        this.router = inject(Router);
        this.appService = inject(AppService);
    }

    ngOnInit() {
        this.queryParams = this.activatedRoute.snapshot.queryParams;
        this.subscriptions.push(this.activatedRoute.data.subscribe(data => {
            this.items = data['items'];

            this.checkPredefinedValues();
        }));

        this.subscriptions.push(this.activatedRoute.queryParams.subscribe(params => {
            this.queryParams = params;
            this.checkPredefinedValues();
        }));


        this.filteredRecipeOptions = this.myRecipeControl.valueChanges.pipe(
            startWith(null),
            map(value => this._filterRecipe(value))
        );

        this.filteredItemOptions = this.myItemControl.valueChanges.pipe(
            startWith(null),
            map(value => this._filterItem(value))
        );


        this.subscriptions.push(this.myRecipeControl.valueChanges.subscribe(() => {
            this.updateQueryParameters();
        }));

        this.subscriptions.push(this.myItemControl.valueChanges.subscribe(value => {
            this.recipes = value && !(typeof value === 'string') ? value.recipes : [];
            this.updateQueryParameters();
        }));

        this.subscriptions.push(this.myAmountControl.valueChanges.subscribe(() => {
            this.updateQueryParameters();
        }));

        this.checkPredefinedValues();
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach(item => item.unsubscribe());
    }

    getDisplayValue(option: ItemRecipe): string {
        return option?.displayName;
    }

    private updateQueryParameters(): void {
        const queryParams: { item?: any, recipe?: any, amount: any } = {
            amount: this.myAmountControl.getRawValue() || 1
        };

        if (this.myItemControl.getRawValue()) {
            queryParams.item = typeof this.myItemControl.getRawValue() === 'string' ? this.myItemControl.getRawValue() : this.myItemControl.getRawValue()?.className;
        }

        if (this.myRecipeControl.getRawValue()) {
            queryParams.recipe = typeof this.myRecipeControl.getRawValue() === 'string' ? this.myRecipeControl.getRawValue() : this.myRecipeControl.getRawValue()?.className;
        }

        this.router.navigateByUrl(`/${this.appService.getGameVersionName()}/planner?${new URLSearchParams(queryParams as unknown as URLSearchParams).toString()}`, {})
    }

    private checkPredefinedValues(): void {
        const item: PlannerItem | null = this.getItemValue();
        if (item) {
            this.myItemControl.setValue(this.getItemValue());
            this.recipes = item.recipes;
        }

        this.myRecipeControl.setValue(this.getRecipeValue());
        this.myAmountControl.setValue(this.getAmountValue());
    }

    private _filterRecipe(value?: ItemRecipe | null | string): Array<ItemRecipe> {
        if (!value) {
            return this.recipes;
        }
        const filterValue = typeof value === 'string' ? value.toLocaleLowerCase() : value.displayName.toLowerCase();

        return this.recipes.filter(recipe => recipe.displayName.toLowerCase().includes(filterValue));
    }

    private _filterItem(value?: PlannerItem | null | string): Array<PlannerItem> {
        if (!value) {
            return this.items;
        }
        const filterValue = typeof value === 'string' ? value.toLocaleLowerCase() : value.displayName.toLowerCase();

        return this.items.filter(item => item.displayName.toLowerCase().includes(filterValue));
    }

    private getRecipeValue(): ItemRecipe | null {
        return null;
    }

    private getItemValue(): PlannerItem | null {
        if (!this.queryParams || !this.queryParams['item']) {
            return null;
        }

        return this.items.find(item => item.className === this.queryParams['item']) || null;
    }

    private getAmountValue(): number {
        return Number(this.queryParams['amount']) || 1;
    }
}
