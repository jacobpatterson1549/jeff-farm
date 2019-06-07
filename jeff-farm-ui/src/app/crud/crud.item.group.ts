import { Type } from '@angular/core';
import { CrudItemGroupViewComponent } from './crud-item-group-view/crud-item-group-view.component';
import { CrudItemGroupFormComponent } from './crud-item-group-form/crud-item-group-form.component';
import { CrudItem } from './crud.item'; // TODO: use hiphens in filename: crud-item.  Same for this class (crud-item-group)

export abstract class CrudItemGroup<V extends CrudItem> extends CrudItem {

    protected static readonly ITEM_NAMES = ['createdDate', 'modifiedDate'];

    public items: V[];

    constructor() {
        super();
    }

    getViewComponent(): Type<any> {
        return CrudItemGroupViewComponent;
    }

    getFormComponent(): Type<any> {
        return CrudItemGroupFormComponent;
    }
}
