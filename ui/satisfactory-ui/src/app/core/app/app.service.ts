import { Injectable, inject } from '@angular/core';
import { AppData, GameVersion, GameVersionType } from './app.data';
import { Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { LocalStorageConstant } from 'src/app/shared/constants/local-storage.constants';

@Injectable({
    providedIn: 'root'
})
export class AppService {
    private httpClient: HttpClient;

    private data!: AppData;

    constructor() {
        this.httpClient = inject(HttpClient);
    }

    resolve(): Observable<AppData> {
        return this.httpClient.get<AppData>('/api/v1/initial')
            .pipe(tap(data => {
                this.data = data;
            }));
    }

    getGameVersions(): Array<GameVersion> {
        return this.data.gameVersions;
    }

    getGameVersion(): string | undefined {
        const storedType: GameVersionType = localStorage.getItem(LocalStorageConstant.GAME_TYPE) as GameVersionType || GameVersionType.EARLY_ACCESS;
        return this.data.gameVersions.find(item => item.type)?.gameVersion;
    }
}
