import { CrudItem } from '../crud/crud.item';
import { FormItem, FormItemType } from '../crud/form.item';
import { CrudItemGroup } from '../crud/crud.item.group';
import { PoultryInspection } from './poultry-inspection';

export class PoultryInspectionGroup extends CrudItemGroup<PoultryInspection> {

    public farmId: number;
    public notes: string;

    constructor(farmId: number) {
        super();
        this.farmId = farmId;
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('notes', FormItemType.String, this.notes), // TODO: make FormItemType.TextArea
        ];
    }

    getDisplayValue(): string {
        return this.createdDate;
    }
}
