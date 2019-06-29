import { FormBuilder, FormGroup } from '@angular/forms';

import { CrudItem } from './crud-item';
import { FormItem, FormItemType } from './form-item';

export class CrudItemCoordinate extends CrudItem {

    public latitude: number;
    public longitude: number;
    public displayOrder: number;

    constructor(mapId: number) {
        super(mapId);
    }

    getFormItems(): FormItem[] {
        return [
            new FormItem('latitude', FormItemType.Integer, this.latitude),
            new FormItem('longitude', FormItemType.Integer, this.longitude),
        ];
    }

    getFormGroup(fb: FormBuilder): FormGroup {
        const formGroup: FormGroup = super.getFormGroup(fb);
        formGroup.addControl('latitude', fb.control(this.latitude));
        formGroup.addControl('longitude', fb.control(this.longitude));
        formGroup.addControl('displayOrder', fb.control(this.displayOrder));
        return formGroup;
    }

    getDisplayValue(): string {
        return `${this.latitude} ${this.longitude}`;
    }
}
