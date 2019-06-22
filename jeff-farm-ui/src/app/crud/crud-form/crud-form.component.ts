import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';

import { CrudItem } from '../crud-item';
import { CrudItemInputComponent } from '../crud-item-input/crud-item-input.component';
import { CrudItemService } from '../crud-item.service';
import { FormItem } from '../form-item';
import { FormType } from '../form-type';

@Component({
  templateUrl: './crud-form.component.html',
})
export class CrudFormComponent<T extends CrudItem> implements OnInit {

  crudItem: T;
  formItems: FormItem[];
  crudForm: FormGroup;
  formType: FormType;
  submitValue: string;
  working = false;
  @ViewChild(CrudItemInputComponent, { static: false }) editor: CrudItemInputComponent<T>;

  constructor(
    private titleService: Title,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private crudItemService: CrudItemService<T>) { }

  ngOnInit() {
    this.formType = this.computeFormType();
    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';
    this.titleService.setTitle(`${(this.formType === FormType.Update) ? 'Update' : 'Create'} ${this.crudItemService.getTypeName()}`);
    this.initCrudItem(this.formType)
      .subscribe((crudItem: T) => {
        this.crudItem = crudItem;
        this.formItems = this.crudItemService.getFormItems(this.crudItem);
        this.crudForm = this.crudItemService.getCrudForm(this.crudItem, this.fb);
      });
  }

  private initCrudItem(formType: FormType): Observable<T> {
    if (formType === FormType.Create) {
      return of(this.crudItemService.createCrudItem());
    }
    if (formType === FormType.Update) {
      return this.crudItemService.get();
    }
  }

  private computeFormType(): FormType {
    const urlParts: string[] = this.router.url.split('/');
    const endPath: string = urlParts[urlParts.length - 1];
    switch (endPath) {
      case 'create':
        return FormType.Create;
      case 'update':
      case 'password':
        return FormType.Update;
      default:
        throw new Error(`Unknown endPath: ${endPath}.  Could not set FormType`);
    }
  }

  onSubmit() {
    if (this.formType === FormType.Create) {
      this.working = true;
      this.crudItemService.post(this.crudItem, this.crudForm.value)
        .pipe(catchError((error: Error) => {
          this.working = false;
          throw error;
        }))
        .subscribe((id: number) => {
          this.route.data.subscribe(data => {
            const relativeLocation: string = data.redirectToParent ? '..' : `../${id}`;
            this.router.navigate([relativeLocation], { relativeTo: this.route });
          });
        });
    }
    if (this.formType === FormType.Update) {
      this.working = true;
      this.crudItemService.put(this.crudItem, this.crudForm.value)
        .pipe(catchError((error: Error) => {
          this.working = false;
          throw error;
        }))
        .subscribe(_ => this.router.navigate(['..'], { relativeTo: this.route }));
    }
  }
}
