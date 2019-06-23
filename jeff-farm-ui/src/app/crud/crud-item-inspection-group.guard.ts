import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

import { CrudItem } from './crud-item';
import { CrudItemInspectionGroup } from './crud-item-inspection-group';
import { CrudItemService } from './crud-item.service';

@Injectable()
export class CrudItemInspectionGroupGuard<T extends CrudItem> implements CanActivate {

  constructor(private crudItemService: CrudItemService<T>) { }

  canActivate(): boolean {
    const crudItem: T = this.crudItemService.createCrudItem();
    const canActivate: boolean = crudItem instanceof CrudItemInspectionGroup;
    return canActivate;
  }
}
