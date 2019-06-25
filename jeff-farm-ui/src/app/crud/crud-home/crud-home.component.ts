import { HeaderService } from 'src/app/header/header.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudItem } from '../crud-item';
import { CrudItemService } from '../crud-item.service';

@Component({
  templateUrl: './crud-home.component.html',
})
export class CrudHomeComponent<T extends CrudItem> implements OnInit {

  crudItemName: string;

  constructor(
    private route: ActivatedRoute,
    private crudItemService: CrudItemService<T>) {

    this.crudItemService.setRoute(this.route);
  }

  ngOnInit() {
    this.crudItemName = this.crudItemService.getTypeName();
  }
}
