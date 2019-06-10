import { CrudItem } from './crud.item'; // TODO: use hiphens in filename: crud-item.  Same for this class (crud-item-group)

export abstract class CrudItemInspection extends CrudItem {

    public groupId: number;
    public targetId: number;
    public targetName: string;

    constructor() {
        super();
    }
}
