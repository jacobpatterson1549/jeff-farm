import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-boolean',
    template: `
    <label [for]="name">{{name | titlecase}}</label>
    <input [id]="name"
    type="checkbox"
    [formControl]="control"
    >`,
})
export class CrudItemInputBooleanComponent {
    @Input() control: FormControl;
    @Input() name: string;
}
