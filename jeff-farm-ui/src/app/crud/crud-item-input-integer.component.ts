import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-integer',
    template: `<span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input
    type="number"
    [formControl]="control"
    >
    </ng-template>`,
})
export class CrudItemInputIntegerComponent {
    @Input() control: FormControl;
}
