import { CrudItem } from '../crud/crud-item';
import { FormItem, FormItemType } from '../crud/form-item';
import { CrudItemInspectionGroup } from '../crud/crud-item-inspection-group';
import { PoultryInspection } from './poultry-inspection';

export class PoultryInspectionGroup extends CrudItemInspectionGroup<PoultryInspection> {

    public farmId: number;
    public notes: string;

    constructor(farmId: number) {
        super();
        this.farmId = farmId;
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
