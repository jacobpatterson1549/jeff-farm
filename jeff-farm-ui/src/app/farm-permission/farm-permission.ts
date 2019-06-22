import { CrudItem } from '../crud/crud-item';
import { FormItem, FormItemType } from '../crud/form-item';

export class FarmPermission extends CrudItem {

    public userName: string;

    constructor(farmId: number) {
        super(farmId);
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('userName', FormItemType.String, this.userName),
        ];
    }

    getDisplayValue(): string {
        return this.userName;
    }
}
