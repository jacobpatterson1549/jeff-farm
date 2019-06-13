import { Validators, AbstractControl, ValidatorFn } from '@angular/forms';

export enum FormItemType {
    String,
    Boolean,
    Integer,
    Stars,
    Color,
    Password,
    TextArea,
}

export class FormItem {
    public name: string;
    public type: FormItemType;
    public value: string | number | boolean;

    constructor(name: string, type: FormItemType, value?: string | number | boolean) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    get validatorFns(): ValidatorFn[] {
        switch (this.type) {
            case FormItemType.String:
            case FormItemType.Integer:
            case FormItemType.Password:
                return [Validators.required];
            case FormItemType.Stars:
                return [
                    Validators.required,
                    Validators.min(0),
                    Validators.max(5),
                ];
            case FormItemType.Boolean:
            case FormItemType.TextArea:
                return [];
            default:
                throw new Error(`ValidatorsFns not set up for ${this.type}.`);
        }
    }
}
