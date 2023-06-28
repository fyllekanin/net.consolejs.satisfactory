import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './app-views/header/header.component';
import { RouterModule } from '@angular/router';


@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HeaderComponent,
        RouterModule.forRoot([
            {
                path: '',
                loadChildren: () => import('./pages/home/home.module').then(m => m.HomeModule)
            },
            {
                path: 'planner',
                loadChildren: () => import('./pages/planner/planner.module').then(m => m.PlannerModule)
            }
        ])
    ],
    declarations: [
        AppComponent
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
