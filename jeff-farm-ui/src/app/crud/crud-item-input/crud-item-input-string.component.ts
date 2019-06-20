import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-string',
    template: `
    <label [for]="name">{{name | titlecase}}</label>
    <span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input [id]="name"
    type="text"
    [formControl]="control"
    >
    </ng-template>`,
})
export class CrudItemInputStringComponent {
    @Input() control: FormControl;
    @Input() name: string;
}
