import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-boolean',
    template: `<input
    type="checkbox"
    [formControl]="control"
    >`,
})
export class CrudItemInputBooleanComponent {
    @Input() control: FormControl;
}
