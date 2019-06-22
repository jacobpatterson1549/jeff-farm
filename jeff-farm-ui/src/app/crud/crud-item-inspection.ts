import { FormBuilder, FormGroup } from '@angular/forms';

import { CrudItem } from './crud-item';

export abstract class CrudItemInspection<V extends CrudItem> extends CrudItem {

    public targetId: number;
    public targetName: string;

    constructor(groupId: number) {
        super(groupId);
    }

    getFormGroup(fb: FormBuilder): FormGroup {
        const formGroup: FormGroup = super.getFormGroup(fb);
        formGroup.addControl('targetId', fb.control(this.targetId));
        formGroup.addControl('targetName', fb.control(this.targetName));
        return formGroup;
    }
}
