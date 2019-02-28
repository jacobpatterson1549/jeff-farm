import { CrudItem } from './crud.item';
import { FormItem, FormItemType,  } from './form.item';

export class Hive extends CrudItem {

    public static readonly ITEM_NAMES = ['name'].concat(CrudItem.ITEM_NAMES);

    public id: number;
    public farmId: number;
    public name: string;

    constructor() {
        super();
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('name', FormItemType.String, this.name),
        ]
    }
}