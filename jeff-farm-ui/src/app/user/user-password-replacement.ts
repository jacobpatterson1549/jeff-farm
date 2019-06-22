import { FormItem, FormItemType } from '../crud/form-item';
import { User } from './user';

export class UserPasswordReplacement extends User {

    currentPassword: string;
    newPassword: string;

    constructor(id: number) {
        super();
        this.id = id;
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('currentPassword', FormItemType.Password, this.currentPassword),
            new FormItem('newPassword', FormItemType.Password, this.newPassword),
        ];
    }

    getDisplayValue(): string {
        throw new Error('Cannot display value.');
    }
}
