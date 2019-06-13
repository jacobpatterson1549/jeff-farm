import { FormBuilder, FormGroup, AbstractControl, FormArray, ValidatorFn, Validators } from '@angular/forms';
import { CrudItem } from './crud-item';
import { CrudItemInspection } from './crud-item-inspection';

export abstract class CrudItemInspectionGroup<V extends CrudItemInspection<CrudItem>> extends CrudItem {

    public inspectionItems: V[] = [];

    constructor() {
        super();
    }

    getFormGroup(fb: FormBuilder): FormGroup {
        const inspectionItemGroups: FormGroup[] = this.inspectionItems.map((inspectionItem: V) => inspectionItem.getFormGroup(fb));
        const array: FormArray = new FormArray(inspectionItemGroups, [Validators.required]);
        const group: FormGroup = super.getFormGroup(fb);
        group.addControl('inspectionItems', array);
        return group;
    }
}
