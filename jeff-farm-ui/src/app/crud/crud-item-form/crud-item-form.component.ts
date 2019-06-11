import { Component, Input, Inject, OnInit, AfterViewInit } from '@angular/core';

import { FormItem, FormItemType } from '../form.item';
import { CrudItem } from '../crud.item';
import { CrudItemGroup } from '../crud.item.group';
import { CrudService } from '../crud.service';
import { CrudItemGroupsService } from '../crud-item-group.service';

@Component({
  selector: 'app-crud-item-form',
  templateUrl: './crud-item-form.component.html',
  styleUrls: ['./crud-item-form.component.css']
})
export class CrudItemFormComponent<T extends CrudItem> implements OnInit {

  @Input()
  crudItem: T;
  formItems: FormItem[];
  formItemType = FormItemType; // used for the ngSwitch in the template
  passwordFormItems: FormItem[];
  targets: Map<number, string>;

  constructor(private crudService: CrudService<T>) { }

  ngOnInit() {
    this.formItems = this.crudItem.getFormItems();

    this.passwordFormItems = [];
    for (let index = 0; index < this.formItems.length; index++) {
      const formItem: FormItem = this.formItems[index];
      if (formItem.type === FormItemType.Password) {
        const formItem2: FormItem = new FormItem(formItem.name + ' (Verify)', formItem.type, formItem.value);
        index++;
        this.formItems.splice(index, 0, formItem2);
        this.passwordFormItems.push(formItem, formItem2);
      }
    }

    if (this.crudItem instanceof CrudItemGroup && this.crudService instanceof CrudItemGroupsService) {
      this.crudService.getTargets()
        .subscribe((targets: Map<number, string>) => {
          this.targets = targets;
          if (this.crudItem instanceof CrudItemGroup) {
            this.crudItem.inspectionItems
              .forEach(inspectionItem => delete this.targets[inspectionItem.targetId]);
          }
        });
    }
  }

  passwordsMatch(): boolean {
    for (let index = 0; index < this.passwordFormItems.length; index += 2) {
      if (this.passwordFormItems[index].value !== this.passwordFormItems[index + 1].value) {
        return false;
      }
    }

    return true;
  }

  allValidStars(): boolean {
    for (const formItem of this.formItems) {
      if (formItem.type === FormItemType.Stars && this.validStars(formItem)) {
        return false;
      }
    }
    return true;
  }

  validStars(formItem: FormItem) {
    return !(formItem.value >= 1 && formItem.value <= 5);
  }

  isValid(): boolean {
    return this.passwordsMatch()
      && this.allValidStars()
      && (!this.isCrudItemGroupGroup() || this.isValidCrudItemGroup()); // TODO: and inspections valid
  }

  getCrudItem(): T {
    for (const formItem of this.formItems) {
      // exclude added passworditems
      const passwordFormItemIndex = this.passwordFormItems.indexOf(formItem);
      if (passwordFormItemIndex < 0 || passwordFormItemIndex % 2 === 0) {
        this.crudItem[formItem.name] = formItem.value;
      }
    }
    return this.crudItem;
  }

  isCrudItemGroupGroup(): boolean {
    return this.crudItem instanceof CrudItemGroup;
  }

  isValidCrudItemGroup(): boolean {
    return this.crudItem instanceof CrudItemGroup
      && this.crudItem.inspectionItems.length > 0;
  }

  addInspection(targetId: number) {
    if (this.crudItem instanceof CrudItemGroup && this.crudService instanceof CrudItemGroupsService) {
      const inspectionItem = this.crudService.createCrudItemInspection();
      inspectionItem.targetId = targetId;
      inspectionItem.targetName = this.targets[targetId];
      delete this.targets[targetId];
      this.crudItem.inspectionItems.push(inspectionItem);
    }
  }

  removeInspection(targetId: number) {
    if (this.crudItem instanceof CrudItemGroup) {
      for (let targetIndex = 0; targetIndex < this.crudItem.inspectionItems.length; targetIndex++) {
        if (this.crudItem.inspectionItems[targetIndex].targetId === targetId) {
          const inspectionItem = this.crudItem.inspectionItems[targetIndex];
          const targetName = inspectionItem.targetName;
          this.crudItem.inspectionItems.slice(targetIndex, 1);
          this.targets.set(targetId, targetName);
          break;
        }
      }
    }
  }
}
