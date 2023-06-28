import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn } from '@angular/router';
import { PlannerStep } from './planner.model';
import { catchError, Observable, of } from 'rxjs';

@Injectable()
export class PlannerService {
    private httpClient: HttpClient;

    constructor() {
        this.httpClient = inject(HttpClient);
    }

    resolve(recipeClassName: string, amount: number): Observable<PlannerStep | null> {
        return this.httpClient.get<PlannerStep>(`/api/v1/planner/test1/${recipeClassName}/${amount}`)
            .pipe(catchError(() => of(null)));
    }
}

export const plannerResolver: ResolveFn<PlannerStep | null> = (route: ActivatedRouteSnapshot) => {
    return inject(PlannerService).resolve(route.queryParams['recipe'], route.queryParams['amount']);
}
