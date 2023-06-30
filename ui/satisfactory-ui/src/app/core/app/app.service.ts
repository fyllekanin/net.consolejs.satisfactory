import { Injectable, inject } from '@angular/core';
import { AppData, GameVersion, GameVersionName } from './app.data';
import { Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

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
        const keys: Array<string> = Object.keys(GameVersionName);
        const name: string = location.pathname.split('/')[1];
        for (const key of keys) {
            if (GameVersionName[key] === name) {
                return this.data.gameVersions.find(version => version.type === key)?.gameVersion;
            }
        }

        return undefined;
    }
}
