import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { FormItem, FormItemType } from '../form.item';
import { CrudFormEditor } from '../crud-form/crud-form-editor';
import { FormType } from '../form.type';
import { CrudItem } from '../crud.item';
import { CrudService } from '../crud.service';

@Component({
  selector: 'app-crud-item-form',
  templateUrl: './crud-item-form.component.html',
  styleUrls: ['./crud-item-form.component.css']
})
export class CrudItemFormComponent<T extends CrudItem> implements CrudFormEditor<T> {

  crudItem: T;
  formItems: FormItem[];
  formItemType = FormItemType; // used for the ngSwitch in the template
  passwordFormItems: FormItem[];

  constructor(private crudService: CrudService<T>) { }

  setFormType(formType: FormType) {
    this.initCrudItem(formType)
      .subscribe(_ => this.initFormItems());
  }

  private initCrudItem(formType: FormType): Observable<any> {
    if (formType === FormType.Create) {
      return of(this.crudItem = this.crudService.createCrudItem());
    }
    if (formType === FormType.Update) {
      return this.crudService.get()
        .pipe(
          tap(crudItem => this.crudItem = crudItem), // TODO: Move this to tap(), in setFormType?
        );
    }
  }

  private initFormItems() {
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
