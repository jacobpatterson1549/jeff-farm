import { CrudItem } from './crud.item';
import { FormItem, FormItemType,  } from './form.item';

export class QueenBee extends CrudItem {

    public static readonly ITEM_NAMES = ['markColor'].concat(CrudItem.ITEM_NAMES);

    public id: number;
    public hiveId: number;
    public markColor: string;

    constructor(hiveId: number) {
        super();
        this.hiveId = hiveId;
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('markColor', FormItemType.String, this.markColor),  // TODO: make color item type
        ]
    }
}