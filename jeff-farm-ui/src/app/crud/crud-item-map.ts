import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { CrudItem } from './crud-item';
import { CrudItemCoordinate } from './crud-item-coordinate';
import { FormItem } from './form-item';

export class CrudItemMap extends CrudItem {

    public coordinates: CrudItemCoordinate[] = [];
    private targetId: number;
    private targetName: string;

    constructor(parentId: number) {
        super(parentId);
    }

    getFormItems(): FormItem[] {
        // uses custom input.
        return [];
    }

    getDisplayValue(): string {
        return `${this.createdDate} ${this.targetName}`;
    }

    getFormGroup(fb: FormBuilder): FormGroup {
        const coordinateFormGroups: FormGroup[] = this.coordinates
            .map(coordinate => coordinate.getFormGroup(fb));
        const formArray: FormArray = new FormArray(coordinateFormGroups, [Validators.required]);
        const formGroup: FormGroup = super.getFormGroup(fb);
        formGroup.addControl('coordinates', formArray);
        formGroup.addControl('targetId', fb.control(this.targetId, Validators.required));
        formGroup.addControl('targetName', fb.control(this.targetName, Validators.required));
        return formGroup;
    }
}
