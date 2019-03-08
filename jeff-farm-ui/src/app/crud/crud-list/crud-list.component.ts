import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  selector: 'crud-list',
  templateUrl: './crud-list.component.html',
  styleUrls: ['./crud-list.component.css']
})
export class CrudListComponent<T extends CrudItem> implements OnInit {

  crudItems: T[];

  constructor(
    private route: ActivatedRoute,
    private crudService: CrudService<T>) {

    this.crudService.setRoute(this.route);
   }

  ngOnInit() {
    this.getItems();
  }

  getItems(): void {
    this.crudService.getList()
      .subscribe((items: T[]) => this.crudItems = items);
  }
}
