import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

import { CrudChild } from '../crud-child';
import { CrudItem } from '../crud-item';
import { CrudItemMap } from '../crud-item-map';
import { CrudItemService } from '../crud-item.service';

@Component({
  templateUrl: './crud-list.component.html',
})
export class CrudListComponent<T extends CrudItem> implements OnInit {

  crudChildren: CrudChild[];
  crudItemName: string;
  crudItems: T[];
  showMapChart = false;

  constructor(
    private titleService: Title,
    private route: ActivatedRoute,
    private crudItemService: CrudItemService<T>) {

    this.crudItemService.setRoute(this.route);
  }

  ngOnInit() {
    this.crudChildren = this.crudItemService.getCrudGroups();
    this.crudItemName = this.crudItemService.getTypeName();
    this.titleService.setTitle(`${this.crudItemName} List`);
    this.crudItemService.getList().subscribe((items: T[]) => this.crudItems = items);

    const crudItem: T = this.crudItemService.createCrudItem();
    this.showMapChart = crudItem instanceof CrudItemMap;
  }
}
