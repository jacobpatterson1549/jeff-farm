import { CrudItem } from '../crud/crud-item';
import { FormItem, FormItemType } from '../crud/form-item';

export class User extends CrudItem {

    public userName: string;
    public password: string;
    public firstName: string;
    public lastName: string;

    constructor() {
        super();
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('userName', FormItemType.String, this.userName),
            new FormItem('password', FormItemType.Password, this.password),
            new FormItem('firstName', FormItemType.String, this.firstName),
            new FormItem('lastName', FormItemType.String, this.lastName),
        ];
    }

    getDisplayValue(): string {
        return this.userName;
    }
}
