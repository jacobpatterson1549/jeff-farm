import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-password',
    template: `
    <div [formGroup]="control">
    <label [for]="name">{{name | titlecase}}</label>
    <span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input [id]="name"
    type="password"
    formControlName="password1"
    >
    <label [for]="name+'-confirm'">{{name | titlecase}} (confirm)</label>
    <input [id]="name+'-confirm'"
    type="password"
    formControlName="password2"
    >
    <div *ngIf="!control.valid" class="notMatching">Passwords Must Match</div>
    </ng-template>
    </div>`,
    styles: ['.notMatching { color: red; }']
})
export class CrudItemInputPasswordComponent {
    @Input() control: FormGroup;
    @Input() name: string;
}
