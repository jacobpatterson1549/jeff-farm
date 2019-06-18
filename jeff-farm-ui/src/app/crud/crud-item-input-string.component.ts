import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-string',
    template: `<input
    type="text"
    [formControl]="control"
    >`,
})
export class CrudItemInputStringComponent {
    @Input() control: FormControl;
    @Input() isUserName: boolean;
}
