import { Component, OnInit, ComponentFactoryResolver, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { catchError } from 'rxjs/operators';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';
import { FormType } from '../form.type';
import { CrudDisplayDirective } from '../CrudDisplayDirective';
import { CrudFormEditor } from './crud-form-editor';

@Component({
  templateUrl: './crud-form.component.html',
})
export class CrudFormComponent<T extends CrudItem> implements OnInit {

  formType: FormType;
  submitValue: string;
  crudFormEditorComponent: CrudFormEditor<T>;
  working = false;
  @ViewChild(CrudDisplayDirective, {static: false}) viewDirective: CrudDisplayDirective;

  constructor(
    private titleService: Title,
    private componentFactoryResolver: ComponentFactoryResolver,
    private router: Router,
    private route: ActivatedRoute,
    private crudService: CrudService<T>) { }

  ngOnInit() {
    this.formType = this.computeFormType();
    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';
    this.titleService.setTitle(`${(this.formType === FormType.Update) ? 'Update' : 'Create'} ${this.crudService.getSingularName()}`);
    const componentRef = this.viewDirective.viewContainerRef.createComponent(
      this.componentFactoryResolver.resolveComponentFactory(
        this.crudService.createCrudItem().getFormComponent()));
    this.crudFormEditorComponent = componentRef.instance;
    this.crudFormEditorComponent.setFormType(this.formType);
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

  isValid() {
    return this.crudFormEditorComponent.isValid();
  }

  submitForm() {
    const crudItem: T = this.crudFormEditorComponent.getCrudItem();

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
