import { Type } from '@angular/core';
import { FormItem } from './form.item';
import { CrudItemViewComponent } from './crud-item-view/crud-item-view.component';
import { CrudItemFormComponent } from './crud-item-form/crud-item-form.component';

export abstract class CrudItem {

    protected static readonly ITEM_NAMES = ['createdDate', 'modifiedDate'];

    public id: number;
    public createdDate: string;
    public modifiedDate: string;
    public displayValue: string;

    constructor() { }

    abstract getFormItems(): FormItem[];

    abstract getDisplayValue(): string;

    getDisplayFieldNames(): string[] {
        return this.getFormItems()
            .map(formItem => formItem.name)
            .filter(formItemName => !formItemName.endsWith('Id'))
            .concat(['createdDate', 'modifiedDate']);
    }

    getViewComponent(): Type<any> {
        return CrudItemViewComponent;
    }

    getFormComponent(): Type<any> {
        return CrudItemFormComponent;
    }
}
