import { FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';

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

    private getValidatorFns(): ValidatorFn[] {
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
            case FormItemType.Color:
                return [
                    Validators.required,
                    Validators.pattern('^#[0-9a-fA-F]{6}$'), // must be same on server
                ];
            case FormItemType.Boolean:
            case FormItemType.TextArea:
                return [];
            default:
                throw new Error(`ValidatorsFns not set up for ${this.type}.`);
        }
    }

    addTo(formGroup: FormGroup, fb: FormBuilder) {
        formGroup.addControl(this.name, this.createControl(fb));
    }

    createControl(fb: FormBuilder): FormControl {
        return fb.control(
            this.value,
            this.getValidatorFns());
    }
}
