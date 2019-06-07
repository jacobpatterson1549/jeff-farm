import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';

import { CrudService, CrudChild } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  templateUrl: './crud-list.component.html',
})
export class CrudListComponent<T extends CrudItem> implements OnInit {

  crudChildren: CrudChild[];
  crudItemName: string;
  crudItems: T[];

  constructor(
    private titleService: Title,
    private route: ActivatedRoute,
    private crudService: CrudService<T>) {

    this.crudService.setRoute(this.route);
  }

  ngOnInit() {
    this.crudChildren = this.crudService.getCrudGroups();
    this.crudItemName = this.crudService.getSingularName();
    this.titleService.setTitle(`${this.crudService.getPluralName()} List`);

    this.getItems();
  }

  getItems(): void {
    this.crudService.getList()
      .subscribe((items: T[]) => this.crudItems = items);
  }
}
