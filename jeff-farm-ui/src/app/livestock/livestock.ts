import { CrudItem } from '../crud/crud-item';
import { FormItem, FormItemType } from '../crud/form-item';

export class Livestock extends CrudItem {

    public name: string;

    constructor(farmId: number) {
        super(farmId);
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('name', FormItemType.String, this.name),
        ];
    }

    getDisplayValue(): string {
        return this.name;
    }
}
