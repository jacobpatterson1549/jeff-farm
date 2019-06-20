import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-string',
    template: `<span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input
    type="text"
    [formControl]="control"
    >
    </ng-template>`,
})
export class CrudItemInputStringComponent {
    @Input() control: FormControl;
    @Input() isUserName: boolean;
}
