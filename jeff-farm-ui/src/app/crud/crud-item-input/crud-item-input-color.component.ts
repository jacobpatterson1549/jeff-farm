import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-color',
    template: `
    <label [for]="name">{{name | titlecase}}</label>
    <input [id]="name"
    type="color"
    [formControl]="control"
    >`,
})
export class CrudItemInputColorComponent {
    @Input() control: FormControl;
    @Input() name: string;
}
