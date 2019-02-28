import { FormItem } from './form.item';

export abstract class CrudItem {

    public id: number;
    public displayValue: string
    
    constructor() { }

    abstract getFormItems(): FormItem[];
}