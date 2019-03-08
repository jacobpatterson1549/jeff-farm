import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  selector: 'crud-home',
  templateUrl: './crud-home.component.html',
})
export class CrudHomeComponent<T extends CrudItem> implements OnInit {

  private crudItemClassName: string;

  constructor(
    private route: ActivatedRoute,
    private crudService: CrudService<T>) {

    this.crudService.setRoute(this.route);
  }

  ngOnInit() {
    this.crudItemClassName = this.crudService.createCrudItem()
      .getClassName();
  }
}
