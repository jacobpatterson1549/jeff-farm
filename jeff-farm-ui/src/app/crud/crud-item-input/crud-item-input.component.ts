import { Component, Input, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';

import { FormItem, FormItemType } from '../form-item';
import { CrudItem } from '../crud-item';
import { CrudItemService } from '../crud-item.service';
import { CrudItemInspectionGroup } from '../crud-item-inspection-group';

@Component({
  selector: 'app-crud-item-input',
  templateUrl: './crud-item-input.component.html',
  styleUrls: ['./crud-item-input.component.css']
})
export class CrudItemInputComponent<T extends CrudItem> implements OnInit {

  @Input()
  crudItem: T;
  @Input()
  crudForm: FormGroup;
  formItems: FormItem[];
  formItemType = FormItemType; // used for the ngSwitch in the template
  inspectionItems: FormArray;

  ngOnInit() {
    this.formItems = this.crudItem.getFormItems();
    this.inspectionItems = this.crudForm.get('inspectionItems') as FormArray;
  }
}
