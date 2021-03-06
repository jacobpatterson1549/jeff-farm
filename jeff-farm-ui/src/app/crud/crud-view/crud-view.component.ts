import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { CrudChild } from '../crud-child';
import { CrudDeleteComponent } from '../crud-delete/crud-delete.component';
import { CrudItem } from '../crud-item';
import { CrudItemService } from '../crud-item.service';
import { FormItem } from '../form-item';

@Component({
  templateUrl: './crud-view.component.html',
})
export class CrudViewComponent<T extends CrudItem> implements OnInit {

  crudItem: T;
  formItems: FormItem[];
  crudForm: FormGroup;
  canDelete = false;
  canDeleteMessage: string;
  crudChildren: CrudChild[];
  stepsToParent: number;
  canUpdate: boolean;

  constructor(
    private titleService: Title,
    private fb: FormBuilder,
    private modalService: NgbModal,
    private crudItemService: CrudItemService<T>) { }

  ngOnInit() {
    this.crudChildren = this.crudItemService.getCrudChildren();
    this.crudItemService.get()
      .subscribe((crudItem: T) => {
        this.crudItem = crudItem;
        this.formItems = this.crudItemService.getFormItems(this.crudItem);
        this.crudForm = this.crudItemService.getCrudForm(this.crudItem, this.fb);
        this.crudForm.disable(); // This makes the crud-view different from the crud-form.
        this.titleService.setTitle(
          `${this.crudItemService.getTypeName()} ${crudItem.getDisplayValue()} details`);
      });

    this.crudItemService.canDelete()
      .subscribe((canDelete: boolean) => this.canDelete = canDelete);
    this.canDeleteMessage = this.crudItemService.getCanDeleteMessage();
    this.stepsToParent = this.crudItemService.getViewStepsToParent();
    this.canUpdate = this.crudItemService.getCanUpdate();
  }

  deleteCrudItem() {
    const modalRef = this.modalService.open(CrudDeleteComponent);
    modalRef.componentInstance.name = this.crudItem.getDisplayValue();
  }
}
