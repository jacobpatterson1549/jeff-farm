import { CrudItem } from './crud.item';
import { FormItem, FormItemType,  } from './form.item';

export class Farm extends CrudItem {

    public static readonly ITEM_NAMES = ['name', 'location'];

    public id: number;
    public name: string;
    public location: string;

    constructor() {
        super();
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('name', FormItemType.String, this.name),
            new FormItem('location', FormItemType.String, this.location),
        ]
    }
}