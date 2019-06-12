import { CrudItem } from './crud-item';
import { CrudItemInspection } from './crud-item-inspection';
import { CrudItemInspectionGroup } from './crud-item-inspection-group';

export class CrudItemInspectionGroupUpdate<V extends CrudItemInspection<CrudItem>, T extends CrudItemInspectionGroup<V>> {

    constructor(
        public group: T,
        public addItems: V[],
        public removeItemIds: number[]) { }
}
