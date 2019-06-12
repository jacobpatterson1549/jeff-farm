import { CrudItem } from './crud-item';
import { CrudItemInspection } from './crud-item-inspection';

export abstract class CrudItemInspectionGroup<V extends CrudItemInspection<CrudItem>> extends CrudItem {

    public inspectionItems: V[] = [];

    constructor() {
        super();
    }
}
