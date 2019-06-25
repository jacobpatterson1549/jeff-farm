import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemInspectionGroupService } from '../crud/crud-item-inspection-group.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { getHeaderItemTypeName, HeaderItem, HeaderItemType } from '../header/header-item';
import { HeaderService } from '../header/header.service';
import { Hive } from '../hive/hive';
import { HiveService } from '../hive/hive.service';
import { HiveInspection } from './hive-inspection';
import { HiveInspectionGroup } from './hive-inspection-group';

@Injectable()
export class HiveInspectionGroupService
  extends CrudItemInspectionGroupService<Hive, HiveInspection, HiveInspectionGroup> {

    constructor(
      headerService: HeaderService,
      errorMessagesService: ErrorMessagesService,
      http: HttpClient) {
      super(
        HeaderItemType.HIVE_INSPECTION_GROUP,
        headerService,
        errorMessagesService,
        http);
    }

  createCrudItem(): HiveInspectionGroup {
    return new HiveInspectionGroup(+this.getParentId());
  }

  createCrudItemInspection(): HiveInspection {
    return new HiveInspection(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    const headerItems: HeaderItem[] = [];
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(HeaderItemType.FARM)));
    headerItems.push(new HeaderItem(HeaderItemType.FARM, +this.getParentId()));
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(HeaderItemType.HIVE)));
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(this.headerItemType)));
    const id: string = this.getId();
    if (id) {
      headerItems.push(new HeaderItem(this.headerItemType, +id));
    }
    return headerItems;
  }

  protected getBaseUrl(): string {
    return 'hive/inspectionGroup';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
