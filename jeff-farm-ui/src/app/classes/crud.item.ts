import { FormItem } from './form.item';

export abstract class CrudItem {

    protected static readonly ITEM_NAMES = ['createdDate', 'modifiedDate']

    public id: number;
    public createdDate: string;
    public modifiedDate: string;
    public displayValue: string;

    constructor() { }

    abstract getFormItems(): FormItem[];
}