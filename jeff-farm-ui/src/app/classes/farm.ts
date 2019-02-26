import { CrudItem } from './crud.item';

export class Farm extends CrudItem {
    
    constructor(public id: number, public name: string, public location: string) {
        super(id);
    }
}