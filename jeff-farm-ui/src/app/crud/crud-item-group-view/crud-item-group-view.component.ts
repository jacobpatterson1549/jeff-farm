import { Component, OnInit } from '@angular/core';

import { CrudItemViewComponent } from '../crud-item-view/crud-item-view.component';
import { CrudItemGroup } from '../crud.item.group';
import { CrudItem } from '../crud.item';
import { CrudItemGroupsService } from '../crud-item-group.service';

@Component({
  selector: 'app-crud-item-group-view',
  templateUrl: './crud-item-group-view.component.html',
})
export class CrudItemGroupViewComponent<U extends CrudItem, V extends CrudItem, T extends CrudItemGroup<V>>
  extends CrudItemViewComponent<T> implements OnInit {

  selectItems: Map<number, U>;

  constructor(private crudItemGroupsService: CrudItemGroupsService<U, V, T>) {
    super(crudItemGroupsService);
  }

  ngOnInit() {
    this.crudItemGroupsService.getSelectItems()
      .subscribe((selectItems: Map<number, U>) => this.selectItems = selectItems);
  }
}
