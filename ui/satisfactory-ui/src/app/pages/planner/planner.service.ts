import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn } from '@angular/router';
import { ItemRecipe, PlannerStep } from './planner.model';
import { catchError, Observable, of } from 'rxjs';
import { AppService } from 'src/app/core/app/app.service';

@Injectable()
export class PlannerService {
    private httpClient: HttpClient;
    private appService: AppService;

    constructor() {
        this.httpClient = inject(HttpClient);
        this.appService = inject(AppService);
    }

    resolvePlanner(recipeClassName: string, amount: number): Observable<PlannerStep | null> {
        if (!recipeClassName || !amount) {
            return of(null);
        }
        return this.httpClient.get<PlannerStep>(`/api/v1/planner/${this.appService.getGameVersion()}/${recipeClassName}/${amount}`)
            .pipe(catchError(() => of(null)));
    }

    resolveRecipes(): Observable<Array<ItemRecipe>> {
        return this.httpClient.get<Array<ItemRecipe>>(`/api/v1/planner/${this.appService.getGameVersion()}/recipes`)
            .pipe(catchError(() => of([])));
    }
}

export const plannerResolver: ResolveFn<PlannerStep | null> = (route: ActivatedRouteSnapshot) => {
    return inject(PlannerService).resolvePlanner(route.queryParams['recipe'], route.queryParams['amount']);
}

export const recipeResolver: ResolveFn<Array<ItemRecipe>> = () => {
    return inject(PlannerService).resolveRecipes();
}
