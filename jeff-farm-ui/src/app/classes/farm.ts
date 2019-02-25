import { CrudItem } from './crud.item';
import { FormItem } from './form.item';

export class Farm extends CrudItem {
    
    constructor(public id: number, public name: string, public location: string) {
        super(id);
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('id', 'number', this.id),
            new FormItem('name', 'string', this.name),
            new FormItem('location', 'string', this.location)
        ];
    }
}