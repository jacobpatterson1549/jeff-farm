import { CrudItem } from './crud-item';
import { FormBuilder, FormGroup } from '@angular/forms';

export abstract class CrudItemInspection<V extends CrudItem> extends CrudItem {

    public groupId: number;
    public targetId: number;
    public targetName: string;

    constructor() {
        super();
    }

    getFormGroup(fb: FormBuilder): FormGroup {
        const formGroup: FormGroup = super.getFormGroup(fb);
        formGroup.addControl('targetId', fb.control(this.targetId));
        formGroup.addControl('targetName', fb.control(this.targetName));
        return formGroup;
    }
}
