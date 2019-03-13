import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { CrudService } from '../crud.service';

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
    private router: Router,
    private route: ActivatedRoute,
    private crudService: CrudService<T>) { }

  ngOnInit() {
    this.initFormType();
    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';
    this.initCrudItem()
      .subscribe(_ => this.formItems = this.crudItem.getFormItems());
  }

  private initFormType() {
    const urlParts: string[] = this.router.url.split('/');
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

    if (this.formType == FormType.Create) {
      this.crudService.post(this.crudItem)
        .subscribe((id: Number) => this.router.navigate([`../${id}`], { relativeTo: this.route }) );
    }
    if (this.formType == FormType.Update) {
      this.crudService.put(this.crudItem)
        .subscribe(_ => this.router.navigate(['..'], { relativeTo: this.route }) );
    }
  }
}
