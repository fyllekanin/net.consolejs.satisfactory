import { AppComponent } from './app.component';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './app-views/header/header.component';
import { RouterModule } from '@angular/router';
import { CoreModule } from './core/core.module';
import { AppService } from './core/app/app.service';

export function init_app(appService: AppService) {
    return () => appService.resolve();
}

@NgModule({
    imports: [
        CoreModule,
        BrowserModule,
        BrowserAnimationsModule,
        HeaderComponent,
        RouterModule.forRoot([
            {
                path: ':gameVersion',
                children: [
                    {
                        path: '',
                        loadChildren: () => import('./pages/home/home.module').then(m => m.HomeModule)
                    },
                    {
                        path: 'planner',
                        loadChildren: () => import('./pages/planner/planner.module').then(m => m.PlannerModule)
                    }
                ]
            },
            {
                path: '**',
                redirectTo: '/ea'
            }
        ])
    ],
    declarations: [
        AppComponent
    ],
    providers: [
        {
            provide: APP_INITIALIZER,
            useFactory: init_app,
            deps: [AppService],
            multi: true
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
