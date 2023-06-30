import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { GameVersionName, GameVersionType } from 'src/app/core/app/app.data';
import { AppService } from 'src/app/core/app/app.service';

@Component({
    selector: 'app-header',
    standalone: true,
    imports: [
        CommonModule,
        RouterModule,
        MatIconModule,
        MatButtonModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatSelectModule
    ],
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    private appService: AppService;
    private router: Router;

    versions: Array<{ type: GameVersionType, gameVersion: string, name: string }> = [];
    version!: { type: GameVersionType, gameVersion: string, name: string } | undefined;

    constructor() {
        this.appService = inject(AppService);
        this.router = inject(Router);
    }

    ngOnInit(): void {
        this.versions = this.appService.getGameVersions()
            .map(item => ({ type: item.type, gameVersion: item.gameVersion, name: GameVersionName[item.type] }));

        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.version = this.versions.find(item => item.name === event.url.split('/')[1]);
            }
        });
    }

    onChangeGameVersion(type: GameVersionType) {
        const currentPath: string = location.pathname.split('/').slice(2).join('/');
        this.router.navigateByUrl(`/${GameVersionName[type]}/${currentPath}`);
    }
}
