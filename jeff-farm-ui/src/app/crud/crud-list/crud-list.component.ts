import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  selector: 'crud-list',
  templateUrl: './crud-list.component.html',
})
export class CrudListComponent<T extends CrudItem> implements OnInit {

  crudItemName: string;
  crudItems: T[];

  constructor(
    private route: ActivatedRoute,
    private crudService: CrudService<T>) {

    this.crudService.setRoute(this.route);
   }

  ngOnInit() {
    this.crudItemName = this.crudService.getSingularName()

    this.getItems();
  }

  getItems(): void {
    this.crudService.getList()
      .subscribe((items: T[]) => this.crudItems = items);
  }
}
