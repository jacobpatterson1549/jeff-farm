import { User } from './user';
import { FormItem, FormItemType } from '../crud/form-item';

export class UserPasswordReplacement extends User {

    oldPassword: string;
    newPassword: string;

    constructor(id: number) {
        super();
        this.id = id;
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('oldPassword', FormItemType.Password, this.oldPassword),
            new FormItem('newPassword', FormItemType.Password, this.newPassword),
        ];
    }

    getDisplayValue(): string {
        throw new Error('Cannot display value.');
    }
}
