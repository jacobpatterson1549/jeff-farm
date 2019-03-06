import { Component, OnInit, Input } from '@angular/core';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  selector: 'crud-list',
  templateUrl: './crud-list.component.html',
  styleUrls: ['./crud-list.component.css']
})
export class CrudListComponent<T extends CrudItem> implements OnInit {

  crudItems: T[];

  constructor(private crudService: CrudService<T>) { }

  ngOnInit() {
    this.getItems();
  }

  getItems(): void {
    this.crudService.getList()
      .subscribe(items => {
         this.crudItems = items
        });
  }
}
