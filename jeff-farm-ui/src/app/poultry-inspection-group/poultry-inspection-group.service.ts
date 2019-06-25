import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemInspectionGroupService } from '../crud/crud-item-inspection-group.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { getHeaderItemTypeName, HeaderItem, HeaderItemType } from '../header/header-item';
import { HeaderService } from '../header/header.service';
import { Poultry } from '../poultry/poultry';
import { PoultryService } from '../poultry/poultry.service';
import { PoultryInspection } from './poultry-inspection';
import { PoultryInspectionGroup } from './poultry-inspection-group';

@Injectable()
export class PoultryInspectionGroupService
  extends CrudItemInspectionGroupService<Poultry, PoultryInspection, PoultryInspectionGroup> {

  constructor(
    headerService: HeaderService,
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      HeaderItemType.POULTRY_INSPECTION_GROUP,
      headerService,
      errorMessagesService,
      http);
  }

  createCrudItem(): PoultryInspectionGroup {
    return new PoultryInspectionGroup(+this.getParentId());
  }

  createCrudItemInspection(): PoultryInspection {
    return new PoultryInspection(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    const headerItems: HeaderItem[] = [];
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(HeaderItemType.FARM)));
    headerItems.push(new HeaderItem(HeaderItemType.FARM, +this.getParentId()));
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(HeaderItemType.POULTRY)));
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(this.headerItemType)));
    const id: string = this.getId();
    if (id) {
      headerItems.push(new HeaderItem(this.headerItemType, +id));
    }
    return headerItems;
  }

  protected getBaseUrl(): string {
    return 'poultry/inspectionGroup';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
