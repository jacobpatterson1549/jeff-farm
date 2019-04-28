import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Title } from '@angular/platform-browser';

import { CrudService, CrudChild } from '../crud.service';
import { CrudItem } from '../crud.item';
import { FormItemType } from '../form.item';
import { CrudDeleteComponent } from '../crud-delete/crud-delete.component';

@Component({
  selector: 'crud-view',
  templateUrl: './crud-view.component.html',
})
export class CrudViewComponent<T extends CrudItem> implements OnInit {

  crudItem: T;
  displayFieldNames: string[];
  displayFormItemTypes: object;
  formItemType = FormItemType; // used for the ngSwitch in the template
  canDelete: boolean = false;
  crudChildren: CrudChild[];
  crudItemSingularName: string;

  constructor(
    private titleService: Title,
    private modalService: NgbModal,
    private crudService: CrudService<T>) { }

  ngOnInit() {

    this.crudChildren = this.crudService.getCrudChildren();
    this.crudItemSingularName = this.crudService.getSingularName();

    this.crudService.get()
      .subscribe((crudItem: T) => {
        this.crudItem = crudItem;
        this.displayFieldNames = crudItem.getDisplayFieldNames();

        this.displayFormItemTypes = crudItem.getFormItems()
          .filter(formItem => this.displayFieldNames.indexOf(formItem.name) >= 0)
          .reduce((obj, formItem) => {
            obj[formItem.name] = formItem.type;
            return obj;
          }, {});

        this.titleService.setTitle(`${this.crudService.getSingularName()} ${crudItem.getDisplayValue()} details`);
      });

    this.crudService.canDelete()
      .subscribe((canDelete: boolean) => this.canDelete = canDelete);
  }

  deleteCrudItem() {
    const modalRef = this.modalService.open(CrudDeleteComponent);
    modalRef.componentInstance.name = this.crudItem.getDisplayValue();
  }
}
