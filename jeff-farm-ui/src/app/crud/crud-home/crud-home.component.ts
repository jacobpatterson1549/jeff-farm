import { Component, OnInit } from '@angular/core';

import { CrudService } from '../crud.service';

import { CrudItem } from '../crud.item';

@Component({
  selector: 'crud-home',
  templateUrl: './crud-home.component.html',
})
export class CrudHomeComponent<T extends CrudItem> implements OnInit {

  private crudItemClassName: string;

  constructor(private crudService: CrudService<T>) { }

  ngOnInit() {
    this.crudItemClassName = this.crudService.createCrudItem()
      .getClassName();
  }
}
