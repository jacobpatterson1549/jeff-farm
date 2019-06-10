import { Component } from '@angular/core';
import { Observable, of } from 'rxjs';

import { FormItem, FormItemType } from '../form.item';
import { FormType } from '../form.type';
import { CrudItem } from '../crud.item';

@Component({
  selector: 'app-crud-item-form',
  templateUrl: './crud-item-form.component.html',
  styleUrls: ['./crud-item-form.component.css']
})
export class CrudItemFormComponent<T extends CrudItem> {

  crudItem: T;
  formItems: FormItem[];
  formItemType = FormItemType; // used for the ngSwitch in the template
  passwordFormItems: FormItem[];

  constructor() { }

  public initFormItems() {
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
    return this.passwordsMatch() && this.allValidStars();
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
}
