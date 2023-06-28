import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { AppService } from './app/app.service';

@NgModule({
    imports: [
        HttpClientModule
    ],
    declarations: [],
    providers: [
        AppService
    ],
    exports: []
})
export class CoreModule {
}
