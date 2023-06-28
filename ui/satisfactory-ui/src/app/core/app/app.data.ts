
export enum GameVersionType {
    EARLY_ACCESS = 'EARLY_ACCESS',
    EXPERIMENTAL = 'EXPERIMENTAL'
}

export interface GameVersion {
    type: GameVersionType;
    gameVersion: string;
    importedAt: number;
}

export interface AppData {
    gameVersions: Array<GameVersion>;
}
