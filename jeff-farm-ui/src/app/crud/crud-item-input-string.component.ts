import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
    selector: 'app-crud-item-input-string',
    template: `<input
    type="text"
    [formControl]="control"
    [autocomplete]="isUserName ? 'username' : null">`,
})
export class CrudItemInputStringComponent implements OnInit {

    @Input()
    control: FormControl;
    @Input()
    isUserName: boolean;

    constructor() {
        console.log('constructor');
    }

    ngOnInit(): void {
        console.log('ngOnInit');
    }
}
