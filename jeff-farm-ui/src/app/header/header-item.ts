export enum HeaderItemType {
    FARM,
    HIVE,
    HIVE_INSPECTION_GROUP,
    POULTRY,
    POULTRY_INSPECTION_GROUP,
    USER,
}

export class HeaderItem {
    url: string;
    type: HeaderItemType;
    id: number;
    name: string;

    static from(url: string, name: string): HeaderItem {
        return new HeaderItem(url, null, null, name);
    }

    // constructor(url: string, name: string);
    constructor(url: string, type: HeaderItemType, id: number, name?: string) {
        this.url = url;
        this.type = type;
        this.id = id;
        this.name = name;
    }

    getTypeName(): string {
        switch (this.type) {
            case HeaderItemType.FARM: return 'Farm';
            case HeaderItemType.HIVE: return 'Hive';
            case HeaderItemType.HIVE: return 'Poultry';
            case HeaderItemType.HIVE_INSPECTION_GROUP:
            case HeaderItemType.POULTRY_INSPECTION_GROUP: return 'inspection';
            default: return 'unknown';
        }
    }
}
