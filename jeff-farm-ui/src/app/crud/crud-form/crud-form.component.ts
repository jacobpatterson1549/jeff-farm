import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { CrudService } from '../crud.service';
import { NavigationService } from '../../navigation.service';

import { CrudItem } from '../crud.item';
import { FormType } from '../form.type';
import { FormItem, FormItemType } from '../form.item';

@Component({
  selector: 'crud-form',
  templateUrl: './crud-form.component.html',
  styleUrls: ['./crud-form.component.css']
})
export class CrudFormComponent<T extends CrudItem> implements OnInit {

  crudItem: T
  formType: FormType;
  formItems: FormItem[];
  submitValue: string;
  formItemType = FormItemType; // used for the ngSwitch in the template

  constructor(
    private route: ActivatedRoute,
    private crudService: CrudService<T>,
    private navigationService: NavigationService) {

      this.crudService.setRoute(this.route);
    }

  ngOnInit() {
    this.initFormType();
    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';
    this.initCrudItem()
      .subscribe(_ => this.formItems = this.crudItem.getFormItems());
  }

  private initFormType() {
    const urlParts: string[] = this.navigationService.getUrl().split('/');
    const endPath: string = urlParts[urlParts.length - 1];
    switch (endPath) {
      case 'create':
        this.formType = FormType.Create;
        break;
      case 'update':
        this.formType = FormType.Update;
        break;
      default:
        throw new Error(`Unknown endPath: ${endPath}.  Could not set FormType`);
    }
  }

  private initCrudItem(): Observable<any> {
    if (this.formType == FormType.Create) {
      return of(this.crudItem = this.crudService.createCrudItem());
    }
    if (this.formType == FormType.Update) {
      return this.crudService.get()
        .pipe(
          tap(crudItem => this.crudItem = crudItem),
        );
    }
  }

  submitForm() {
    for (let formItem of this.formItems) {
      this.crudItem[formItem.name] = formItem.value;
    }

    let result: Observable<any>;
    if (this.formType == FormType.Create) {
      result = this.crudService.post(this.crudItem);
    }
    if (this.formType == FormType.Update) {
      result = this.crudService.put(this.crudItem);
    }
    result.subscribe(_ => this.navigationService.goBack() );
  }
}
