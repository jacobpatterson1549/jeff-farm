import { CrudItem } from '../crud/crud.item';
import { FormItem, FormItemType } from '../crud/form.item';

export class Poultry extends CrudItem {

    public farmId: number;
    public name: string;

    constructor(farmId: number) {
        super();
        this.farmId = farmId;
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
