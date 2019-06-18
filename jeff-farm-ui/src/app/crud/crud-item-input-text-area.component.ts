import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-text-area',
    template: `<span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <textarea
    type="text"
    [formControl]="control"
    rows="2"
    cols="20"
    ></textarea>
    </ng-template>`,
    styles: ['span { white-space: pre-wrap; }']
})
export class CrudItemInputTextAreaComponent {
    @Input() control: FormControl;
}
