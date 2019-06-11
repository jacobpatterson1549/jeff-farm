import { CrudItem } from './crud-item';

export abstract class CrudItemInspection<V extends CrudItem> extends CrudItem {

    public groupId: number;
    public targetId: number;
    public targetName: string;

    constructor() {
        super();
    }
}
