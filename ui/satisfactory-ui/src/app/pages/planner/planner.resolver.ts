import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn } from '@angular/router';
import { PlannerStep } from './planner.model';
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

    resolve(recipeClassName: string, amount: number): Observable<PlannerStep | null> {
        return this.httpClient.get<PlannerStep>(`/api/v1/planner/${this.appService.getGameVersion()}/${recipeClassName}/${amount}`)
            .pipe(catchError(() => of(null)));
    }
}

export const plannerResolver: ResolveFn<PlannerStep | null> = (route: ActivatedRouteSnapshot) => {
    return inject(PlannerService).resolve(route.queryParams['recipe'], route.queryParams['amount']);
}
