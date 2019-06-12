import { Component, OnInit, ViewChild, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

import { CrudItemService } from '../crud-item.service';
import { CrudItem } from '../crud-item';
import { FormType } from '../form-type';
import { CrudItemFormComponent } from '../crud-item-form/crud-item-form.component';
import { CrudItemGroup } from '../crud-item-group';
import { CrudItemGroupUpdate } from '../crud-item-group-update';
import { CrudItemGroupService } from '../crud-item-group.service';

@Component({
  templateUrl: './crud-form.component.html',
})
export class CrudFormComponent<T extends CrudItem> implements OnInit, AfterViewInit {

  crudItem: T;
  formType: FormType;
  submitValue: string;
  working = false;
  @ViewChild(CrudItemFormComponent, { static: false }) editor: CrudItemFormComponent<T>;
  initialized = false;

  constructor(
    private titleService: Title,
    private router: Router,
    private route: ActivatedRoute,
    private crudItemService: CrudItemService<T>) { }

  ngOnInit() {
    this.formType = this.computeFormType();
    this.submitValue = (this.formType === FormType.Update) ? 'Update' : 'Submit';
    this.titleService.setTitle(`${(this.formType === FormType.Update) ? 'Update' : 'Create'} ${this.crudItemService.getSingularName()}`);
    this.initCrudItem(this.formType)
      .subscribe((crudItem: T) => {
        this.crudItem = crudItem;
      });
  }

  ngAfterViewInit() {
    this.initialized = this.crudItem != null;
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
        return FormType.Update;
      default:
        throw new Error(`Unknown endPath: ${endPath}.  Could not set FormType`);
    }
  }

  submitForm() {
    const crudItem: T = this.editor.getCrudItem();

    if (this.formType === FormType.Create) {
      this.working = true;
      this.crudItemService.post(crudItem)
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
      let putRequest: Observable<any>;
      if (crudItem instanceof CrudItemGroup && this.crudItemService instanceof CrudItemGroupService) {
        const addItems = [];
        this.editor.addItemTargetIds.forEach((targetId: number) => {
          const itemIndex: number = crudItem.inspectionItems.findIndex(item => item.targetId === targetId);
          addItems.push(crudItem.inspectionItems[itemIndex]);
          crudItem.inspectionItems.splice(itemIndex, 1);
        });
        const removeItemIds = this.editor.removeItemIds;
        const groupUpdate = new CrudItemGroupUpdate(crudItem, addItems, removeItemIds);
        putRequest = this.crudItemService.putUpdate(groupUpdate);
      } else {
        putRequest = this.crudItemService.put(crudItem);
      }
      this.working = true;
      putRequest.pipe(catchError((error: Error) => {
        this.working = false;
        throw error;
      }))
        .subscribe(_ => this.router.navigate(['..'], { relativeTo: this.route }));
    }
  }
}
