import { CrudItem } from './crud.item';
import { FormItem, FormItemType,  } from './form.item';

export class Hive extends CrudItem {

    public static readonly ITEM_NAMES = ['name','queenColor'].concat(CrudItem.ITEM_NAMES);

    public farmId: number;
    public name: string;
    public queenColor: string;

    constructor(farmId: number) {
        super();
        this.farmId = farmId;
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('name', FormItemType.String, this.name),
            new FormItem('queenColor', FormItemType.Color, this.queenColor),
        ]
    }
}