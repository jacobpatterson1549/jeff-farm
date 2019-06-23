import { CrudItemInspectionGroup } from '../crud/crud-item-inspection-group';
import { FormItem, FormItemType } from '../crud/form-item';
import { HiveInspection } from './hive-inspection';

export class HiveInspectionGroup extends CrudItemInspectionGroup<HiveInspection> {

    public notes: string;

    constructor(farmId: number) {
        super(farmId);
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('notes', FormItemType.TextArea, this.notes),
        ];
    }

    getDisplayValue(): string {
        return this.createdDate;
    }
}
