import { CrudItemInspection } from '../crud/crud-item-inspection';
import { FormItem, FormItemType } from '../crud/form-item';
import { Poultry } from '../poultry/poultry';

export class PoultryInspection extends CrudItemInspection<Poultry> {

    public birdCount: number;
    public eggCount: number;

    constructor(groupId: number) {
        super(groupId);
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('birdCount', FormItemType.Integer, this.birdCount),
            new FormItem('eggCount', FormItemType.Integer, this.eggCount),
        ];
    }

    getDisplayValue(): string {
        return this.targetName.toString();
    }
}
