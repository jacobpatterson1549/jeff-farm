import { Component, OnInit, ViewChild, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';
import { FormType } from '../form.type';
import { CrudItemFormComponent } from '../crud-item-form/crud-item-form.component';

@Component({
  templateUrl: './crud-form.component.html',
})
export class CrudFormComponent<T extends CrudItem> implements OnInit, AfterViewInit {

  crudItem: T;
  formType: FormType;
  submitValue: string;
  working = false;
  @ViewChild(CrudItemFormComponent, {static: false}) editor: CrudItemFormComponent<T>;
  initialized = false;

  constructor(
    private titleService: Title,
    private router: Router,
    private route: ActivatedRoute,
    private crudService: CrudService<T>) { }

  ngOnInit() {
    this.formType = this.computeFormType();
    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';
    this.titleService.setTitle(`${(this.formType === FormType.Update) ? 'Update' : 'Create'} ${this.crudService.getSingularName()}`);
    this.initCrudItem(this.formType)
      .subscribe((crudItem: T) => {
        this.crudItem = crudItem;
      });
  }

  ngAfterViewInit() {
    this.initialized = this.crudItem != null;
  }

  getCrudItemCopy(): T {
    return Object.assign(this.crudService.createCrudItem(), JSON.parse(JSON.stringify(this.crudItem)));
  }

  private initCrudItem(formType: FormType): Observable<T> {
    if (formType === FormType.Create) {
      return of(this.crudService.createCrudItem());
    }
    if (formType === FormType.Update) {
      return this.crudService.get();
    }
  }

  private computeFormType(): FormType {
    const urlParts: string[] = this.router.url.split('/');
    const endPath: string = urlParts[urlParts.length - 1];
    switch (endPath) {
      case 'create':
        return FormType.Create;
      case 'update':
        return FormType.Update;
      default:
        throw new Error(`Unknown endPath: ${endPath}.  Could not set FormType`);
    }
  }

  submitForm() {
    const crudItem: T = this.editor.getCrudItem();

    if (this.formType === FormType.Create) {
      this.working = true;
      this.crudService.post(crudItem)
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
      this.crudService.put(crudItem)
        .pipe(catchError((error: Error) => {
          this.working = false;
          throw error;
        }))
        .subscribe(_ => this.router.navigate(['..'], { relativeTo: this.route }));
    }
  }
}
