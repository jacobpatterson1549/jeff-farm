export enum HeaderItemType {
    FARM,
    FARM_PERMISSION,
    HIVE,
    HIVE_INSPECTION_GROUP,
    POULTRY,
    POULTRY_INSPECTION_GROUP,
    USER,
}

export class HeaderItem {
    type: HeaderItemType;
    id: number;
    name: string;

    static from(name: string): HeaderItem {
        return new HeaderItem(null, null, name);
    }

    // constructor(url: string, name: string);
    constructor(type: HeaderItemType, id: number, name?: string) {
        this.type = type;
        this.id = id;
        this.name = name;
    }
}

export function getHeaderItemTypeName(headerItemType: HeaderItemType): string {
    switch (headerItemType) {
        case HeaderItemType.FARM: return 'Farm';
        case HeaderItemType.FARM_PERMISSION: return 'Permission';
        case HeaderItemType.HIVE: return 'Hive';
        case HeaderItemType.HIVE_INSPECTION_GROUP: return 'Hive Inspection';
        case HeaderItemType.POULTRY: return 'Poultry';
        case HeaderItemType.POULTRY_INSPECTION_GROUP: return 'Poultry Inspection';
        default: return 'unknown';
    }
}
