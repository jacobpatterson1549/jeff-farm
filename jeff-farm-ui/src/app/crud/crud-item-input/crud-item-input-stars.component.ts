import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-stars',
    template: `
    <label [for]="name">{{name | titlecase}}</label>
    <ngb-rating [id]="name"
    [formControl]="control"
    max="5"
    resettable="true"
    ></ngb-rating>`,
})
export class CrudItemInputStarsComponent {
    @Input() control: FormControl;
    @Input() name: string;
}
