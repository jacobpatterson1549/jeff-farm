import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-stars',
    template: `<ngb-rating
    [formControl]="control"
    max="5"
    resettable="true"
    ></ngb-rating>`,
})
export class CrudItemInputStarsComponent {
    @Input() control: FormControl;
}
