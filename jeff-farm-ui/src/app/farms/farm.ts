import { CrudItem } from '../crud/crud.item';
import { FormItem, FormItemType } from '../crud/form.item';

export class Farm extends CrudItem {

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

    getDisplayValue(): string {
        return this.name;
    }
}
