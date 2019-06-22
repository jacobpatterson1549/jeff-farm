import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';

import { CrudItem } from '../crud-item';
import { FormItem, FormItemType } from '../form-item';

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
