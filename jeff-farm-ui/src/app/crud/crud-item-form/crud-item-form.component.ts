import { Component, Input, OnInit, QueryList, ViewChildren, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormArray, AbstractControl, FormBuilder, ValidatorFn } from '@angular/forms';

import { FormItem, FormItemType } from '../form-item';
import { CrudItem } from '../crud-item';
import { CrudItemService } from '../crud-item.service';
import { CrudItemGroupService } from '../crud-item-inspection-group.service';

@Component({
  selector: 'app-crud-item-form',
  templateUrl: './crud-item-form.component.html',
  styleUrls: ['./crud-item-form.component.css']
})
export class CrudItemFormComponent<T extends CrudItem> implements OnInit {

  @Input()
  crudItem: T;
  @Input()
  crudForm: FormGroup;
  formItems: FormItem[];
  formItemType = FormItemType; // used for the ngSwitch in the template
  selectTargets;
  objectKeys = Object.keys; // used in the template
  @ViewChildren(CrudItemFormComponent) groupEditors: QueryList<CrudItemFormComponent<T>>;

  constructor(
    private fb: FormBuilder,
    private crudItemService: CrudItemService<T>) { }

  ngOnInit() {
    this.formItems = this.crudItem.getFormItems();

    if (this.inspectionItems != null && this.crudItemService instanceof CrudItemGroupService) {
      this.crudItemService.getTargets()
        .subscribe((targets: Map<number, string>) => {
          this.selectTargets = {};
          this.selectTargets[0] = ' ';
          for (const [targetId, targetName] of Object.entries(targets)) {
            this.selectTargets[+targetId] = targetName;
          }
          this.inspectionItems.controls
            .forEach((inspectionItem: AbstractControl) => {
              const targetId: number = inspectionItem.get('targetId').value;
              delete this.selectTargets[targetId];
            });
        });
    }
  }

  get inspectionItems(): FormArray {
    return this.crudForm.get('inspectionItems') as FormArray;
  }

  addInspectionItem(targetIndex: number) {
    if (targetIndex > 0 // not blank item
      && this.inspectionItems != null
      && this.crudItemService instanceof CrudItemGroupService) {
      const inspectionItem: FormGroup = this.crudItemService.createCrudItemInspection().getFormGroup(this.fb);
      const addTargetId: number = +Object.keys(this.selectTargets)[targetIndex];
      inspectionItem.patchValue({
        targetId: addTargetId,
        targetName: this.selectTargets[addTargetId]
      });
      delete this.selectTargets[addTargetId];
      this.inspectionItems.push(inspectionItem);
    }
  }

  removeInspectionItem(removeTargetIdStr: string) {
    if (this.inspectionItems != null) {
      const targeremoveTargetId = +removeTargetIdStr;
      for (let i = 0; i < this.inspectionItems.length; i++) {
        const inspectionItem: FormGroup = this.inspectionItems.at(i) as FormGroup;
        if (inspectionItem.get('targetId').value === targeremoveTargetId) {
          this.selectTargets[targeremoveTargetId] = inspectionItem.get('targetName').value;
          this.inspectionItems.removeAt(i);
          break;
        }
      }
    }
  }
}
