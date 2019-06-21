import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';

class PasswordFormGroup extends FormGroup {
    constructor(passwordControl: FormControl, fb: FormBuilder) {
        super({
            password1: fb.control('', Validators.required),
            password2: fb.control('', Validators.required),
        }, { validators: (group: FormGroup): ValidationErrors | null => {
            const password1 = group.get('password1');
            const password2 = group.get('password2');
            const sameValues: boolean = password1 && password2 && password1.value === password2.value;
            passwordControl.setValue(sameValues ? password1.value : null);
            return sameValues ? null : { passwordsDifferent: true };
        } });
    }
}

@Component({
    selector: 'app-crud-item-input-password',
    template: `
    <div [formGroup]="passwordGroup">
    <label [for]="name">{{name | titlecase}}</label>
    <span *ngIf="control.disabled; else enabled">{{control.value}}</span>
    <ng-template #enabled>
    <input [id]="name"
    type="password"
    formControlName="password1"
    >
    <br/>
    <label [for]="name+'-confirm'">{{name | titlecase}} (confirm)</label>
    <input [id]="name+'-confirm'"
    type="password"
    formControlName="password2"
    >
    <div *ngIf="passwordGroup.dirty && !passwordGroup.valid" class="notMatching">Passwords Must Match</div>
    </ng-template>
    </div>`,
    styles: [
        '.notMatching { color: red; }',
    ]
})
export class CrudItemInputPasswordComponent implements OnInit {
    @Input() control: FormControl;
    @Input() name: string;
    passwordGroup: FormGroup;

    constructor(private fb: FormBuilder) { }

    ngOnInit(): void {
        this.passwordGroup = new PasswordFormGroup(this.control, this.fb);
    }
}
