import { CrudItem } from './crud-item';
import { CrudItemInspection } from './crud-item-inspection';
import { CrudItemGroup } from './crud-item-group';

export class CrudItemGroupUpdate<V extends CrudItemInspection<CrudItem>, T extends CrudItemGroup<V>> {

    constructor(
        public group: T,
        public addItems: V[],
        public removeItemIds: number[]) { }
}
