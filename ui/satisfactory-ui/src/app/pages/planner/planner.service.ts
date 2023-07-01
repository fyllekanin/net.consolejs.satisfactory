import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn } from '@angular/router';
import { PlannerItem, PlannerStep } from './planner.model';
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

    resolvePlanner(itemClassName: string, amount: number, recipeClassName: string): Observable<PlannerStep | null> {
        if (!itemClassName || !amount) {
            return of(null);
        }
        const queryParams: string = recipeClassName ? `?recipeClassName=${recipeClassName}` : '';
        return this.httpClient.get<PlannerStep>(`/api/v1/planner/${this.appService.getGameVersion()}/${itemClassName}/${amount}${queryParams}`)
            .pipe(catchError(() => of(null)));
    }

    resolveItems(): Observable<Array<PlannerItem>> {
        return this.httpClient.get<Array<PlannerItem>>(`/api/v1/planner/${this.appService.getGameVersion()}/items`)
            .pipe(catchError(() => of([])));
    }
}

export const plannerResolver: ResolveFn<PlannerStep | null> = (route: ActivatedRouteSnapshot) => {
    return inject(PlannerService).resolvePlanner(route.queryParams['item'], route.queryParams['amount'], route.queryParams['recipe']);
}

export const itemsResolver: ResolveFn<Array<PlannerItem>> = () => {
    return inject(PlannerService).resolveItems();
}
