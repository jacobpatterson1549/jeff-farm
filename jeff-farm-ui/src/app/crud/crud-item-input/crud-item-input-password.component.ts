import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-password',
    template: `
    <label [for]="name">{{name | titlecase}}</label>
    <span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input [id]="name"
    type="password"
    [formControl]="control"
    >
    </ng-template>`,
})
export class CrudItemInputPasswordComponent {
    @Input() control: FormControl;
    @Input() name: string;
}
