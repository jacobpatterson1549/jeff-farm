import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-password',
    template: `<input
    type="password"
    [formControl]="control"
    >`,
})
export class CrudItemInputPasswordComponent {
    @Input() control: FormControl;
}
