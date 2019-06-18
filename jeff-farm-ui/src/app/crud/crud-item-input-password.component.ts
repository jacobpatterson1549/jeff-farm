import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-password',
    template: `<span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input
    type="password"
    [formControl]="control"
    >
    </ng-template>`,
})
export class CrudItemInputPasswordComponent {
    @Input() control: FormControl;
}
