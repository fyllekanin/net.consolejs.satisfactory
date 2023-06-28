import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { GameVersionType } from 'src/app/core/app/app.data';
import { AppService } from 'src/app/core/app/app.service';
import { LocalStorageConstant } from 'src/app/shared/constants/local-storage.constants';

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
    private activatedRoute: ActivatedRoute;

    versions: Array<{ type: GameVersionType, gameVersion: string }> = [];
    version!: { type: GameVersionType, gameVersion: string } | undefined;

    constructor() {
        this.appService = inject(AppService);
        this.router = inject(Router);
        this.activatedRoute = inject(ActivatedRoute);
    }

    ngOnInit(): void {
        this.versions = this.appService.getGameVersions()
            .map(item => ({ type: item.type, gameVersion: item.gameVersion }));

        this.version = this.versions.find(item => item.gameVersion === this.appService.getGameVersion());
    }

    onChangeGameVersion(type: GameVersionType) {
        localStorage.setItem(LocalStorageConstant.GAME_TYPE, type);

        // changes the route without moving from the current view or
        // triggering a navigation event,
        this.router.navigate([], {
            relativeTo: this.activatedRoute,
            queryParams: {
                gameVersion: this.appService.getGameVersion()
            },
            queryParamsHandling: 'merge',
            // preserve the existing query params in the route
        });
    }
}
