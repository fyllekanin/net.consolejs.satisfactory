
export enum GameVersionType {
    EARLY_ACCESS = 'EARLY_ACCESS',
    EXPERIMENTAL = 'EXPERIMENTAL'
}

export const GameVersionName: { [key: string]: string } = {
    EARLY_ACCESS: 'ea',
    EXPERIMENTAL: 'ep'
}

export interface GameVersion {
    type: GameVersionType;
    gameVersion: string;
    importedAt: number;
}

export interface AppData {
    gameVersions: Array<GameVersion>;
}
