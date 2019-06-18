import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-integer',
    template: `<input
    type="number"
    [formControl]="control"
    >`,
})
export class CrudItemInputIntegerComponent {
    @Input() control: FormControl;
}
