import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-text-area',
    template: `<textarea
    type="text"
    [formControl]="control"
    rows="2"
    cols="20"
    ></textarea>`,
})
export class CrudItemInputTextAreaComponent {
    @Input() control: FormControl;
}
