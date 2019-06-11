import { FormItem } from './form-item';

export abstract class CrudItem {

    protected static readonly ITEM_NAMES = ['createdDate', 'modifiedDate'];

    public id: number;
    public createdDate: string;
    public modifiedDate: string;
    public displayValue: string;

    constructor() { }

    abstract getFormItems(): FormItem[];

    abstract getDisplayValue(): string;

    getDisplayFieldNames(includeDates: boolean): string[] {
        let displayFieldNames: string[] = this.getFormItems()
            .map(formItem => formItem.name)
            .filter(formItemName => !formItemName.endsWith('Id'));
        if (includeDates) {
           displayFieldNames = displayFieldNames.concat(CrudItem.ITEM_NAMES);
        }
        return displayFieldNames;
    }
}
