import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-color',
    template: `<input
    type="color"
    [formControl]="control"
    >`,
})
export class CrudItemInputColorComponent {
    @Input() control: FormControl;
}
