import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-integer',
    template: `
    <label [for]="name">{{name | titlecase}}</label>
    <span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input [id]="name"
    type="number"
    [formControl]="control"
    >
    </ng-template>`,
})
export class CrudItemInputIntegerComponent {
    @Input() control: FormControl;
    @Input() name: string;
}
