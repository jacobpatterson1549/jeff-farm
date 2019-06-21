import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';

import { FormItem, FormItemType } from '../form-item';
import { CrudItem } from '../crud-item';

@Component({
  selector: 'app-crud-item-input',
  templateUrl: './crud-item-input.component.html',
})
export class CrudItemInputComponent<T extends CrudItem> implements OnInit {

  @Input()
  formItems: FormItem[];
  @Input()
  crudForm: FormGroup;
  formItemType = FormItemType; // used for the ngSwitch in the template
  inspectionItems: FormArray;

  ngOnInit() {
    this.inspectionItems = this.crudForm.get('inspectionItems') as FormArray;
  }
}
