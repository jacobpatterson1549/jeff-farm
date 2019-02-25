import { FormItem } from './form.item';

export abstract class CrudItem {

    constructor(public id: number) { }

    abstract getFormItems(): FormItem[];
}