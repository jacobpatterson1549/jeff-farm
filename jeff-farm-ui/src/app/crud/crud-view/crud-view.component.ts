import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Title } from '@angular/platform-browser';

import { CrudItemService } from '../crud-item.service';
import { CrudChild } from '../crud-child';
import { CrudItem } from '../crud-item';
import { CrudDeleteComponent } from '../crud-delete/crud-delete.component';

@Component({
  templateUrl: './crud-view.component.html',
})
export class CrudViewComponent<T extends CrudItem> implements OnInit {

  crudItem: T;
  canDelete = false;
  crudChildren: CrudChild[];
  crudItemSingularName: string;

  constructor(
    private titleService: Title,
    private modalService: NgbModal,
    private crudItemService: CrudItemService<T>) { }

  ngOnInit() {
    this.crudChildren = this.crudItemService.getCrudChildren();
    this.crudItemSingularName = this.crudItemService.getTypeName();
    this.crudItemService.get()
    .subscribe((crudItem: T) => {
      this.crudItem = crudItem;
      this.titleService.setTitle(`${this.crudItemSingularName} ${crudItem.getDisplayValue()} details`);
      });

    this.crudItemService.canDelete()
      .subscribe((canDelete: boolean) => this.canDelete = canDelete);
  }

  deleteCrudItem() {
    const modalRef = this.modalService.open(CrudDeleteComponent);
    modalRef.componentInstance.name = this.crudItem.getDisplayValue();
  }
}
