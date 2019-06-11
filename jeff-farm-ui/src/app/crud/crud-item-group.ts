import { CrudItem } from './crud-item';
import { CrudItemInspection } from './crud-item-inspection';

// TODO: rename to CrudItemInspectionGroup
export abstract class CrudItemGroup<V extends CrudItemInspection<CrudItem>> extends CrudItem {

    public inspectionItems: V[] = [];

    constructor() {
        super();
    }
}
