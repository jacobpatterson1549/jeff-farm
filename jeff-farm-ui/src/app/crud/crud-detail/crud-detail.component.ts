import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudService } from '../crud.service';
import { CrudItem } from '../crud.item';

@Component({
  templateUrl: './crud-detail.component.html',
})
export class CrudDetailComponent<T extends CrudItem> implements OnInit {

  crudItemName: string;

  constructor(
    private route: ActivatedRoute,
    private crudService: CrudService<T>) {

    this.crudService.setRoute(this.route);
  }

  ngOnInit() {
    this.crudService.get()
      .subscribe((crudItem: CrudItem) => {
        this.crudItemName = crudItem.getDisplayValue();
      });
  }
}
