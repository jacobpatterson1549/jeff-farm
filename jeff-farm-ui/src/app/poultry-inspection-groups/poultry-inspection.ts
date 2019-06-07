import { CrudItem } from '../crud/crud.item';
import { FormItem, FormItemType } from '../crud/form.item';

export class PoultryInspection extends CrudItem {

    public poultryInspectionGroupId: number;
    public poultryId: number;
    public birdCount: number;
    public eggCount: number;

    constructor(
        poultryInspectionGroupId: number,
        poultryId: number,
        birdCount: number,
        eggCount: number) {
        super();
        this.poultryInspectionGroupId = poultryInspectionGroupId;
        this.poultryId = poultryId;
        this.birdCount = birdCount;
        this.eggCount = eggCount;
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('birdCount', FormItemType.Integer, this.birdCount),
            new FormItem('eggCount', FormItemType.Integer, this.eggCount),
        ];
    }

    getDisplayValue(): string {
        return this.poultryId.toString();
    }
}
