import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CrudItemService } from '../crud-item.service';
import { CrudItem } from '../crud-item';

@Component({
  templateUrl: './crud-detail.component.html',
})
export class CrudDetailComponent<T extends CrudItem> implements OnInit {

  crudItemName: string;

  constructor(
    private route: ActivatedRoute,
    private crudItemService: CrudItemService<T>) {

    this.crudItemService.setRoute(this.route);
  }

  ngOnInit() {
    this.crudItemService.get()
      .subscribe((crudItem: CrudItem) => {
        this.crudItemName = crudItem.getDisplayValue();
      });
  }
}
