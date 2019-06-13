import { FormItem, FormItemType } from './form-item';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
            .filter(formItemName => !formItemName.endsWith('Id')); // TODO: is this needed?
        if (includeDates) {
           displayFieldNames = displayFieldNames.concat(CrudItem.ITEM_NAMES);
        }
        return displayFieldNames;
    }

    getFormGroup(fb: FormBuilder): FormGroup {
        const formGroup: FormGroup = new FormGroup({});
        this.getFormItems().forEach((formItem: FormItem) => formGroup.addControl(
            formItem.name,
            fb.control(
                formItem.value,
                formItem.validatorFns)));
        formGroup.addControl('id', fb.control(this.id));
        return formGroup;
    }

    getFormItemType(formItemName: string): FormItemType {
        return this.getFormItems()
            .filter((formItem: FormItem) => formItem.name === formItemName)[0]
            .type;
    }
}