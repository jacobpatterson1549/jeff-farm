import { CrudItem } from './crud.item';
import { CrudItemInspection } from './crud.item.inspection';

export abstract class CrudItemGroup<V extends CrudItemInspection> extends CrudItem {

    public items: V[];

    constructor() {
        super();
    }
}
