import { FormItem, FormItemType } from '../crud/form.item';
import { CrudItemInspection } from '../crud/crud.item.inspection';
import { Poultry } from '../poultry/poultry';

export class PoultryInspection extends CrudItemInspection<Poultry> {

    public birdCount: number;
    public eggCount: number;

    constructor(
        birdCount: number,
        eggCount: number) {
        super();
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
        return this.targetName.toString();
    }
}
