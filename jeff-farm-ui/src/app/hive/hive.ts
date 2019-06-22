import { CrudItem } from '../crud/crud-item';
import { FormItem, FormItemType } from '../crud/form-item';

export class Hive extends CrudItem {

    public name: string;
    public queenColor: string;

    constructor(farmId: number) {
        super(farmId);
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('name', FormItemType.String, this.name),
            new FormItem('queenColor', FormItemType.Color, this.queenColor),
        ];
    }

    getDisplayValue(): string {
        return this.name;
    }
}
