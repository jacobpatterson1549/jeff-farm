import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { FarmService } from '../farm/farm.service';
import { getHeaderItemTypeName, HeaderItem, HeaderItemType } from '../header/header-item';
import { HeaderService } from '../header/header.service';
import { FarmPermission } from './farm-permission';

@Injectable()
export class FarmPermissionService extends CrudItemService<FarmPermission> {

  constructor(
    headerService: HeaderService,
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      HeaderItemType.FARM_PERMISSION,
      headerService,
      errorMessagesService,
      http);
  }

  createCrudItem(): FarmPermission {
    return new FarmPermission(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    const headerItems: HeaderItem[] = [];
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(HeaderItemType.FARM)));
    headerItems.push(new HeaderItem(HeaderItemType.FARM, +this.getParentId()));
    headerItems.push(HeaderItem.from(getHeaderItemTypeName(this.headerItemType)));
    const id: string = this.getId();
    if (id) {
      headerItems.push(new HeaderItem(this.headerItemType, +id));
    }
    return headerItems;
  }

  protected getBaseUrl(): string {
    return 'farm/permission';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }

  getCanUpdate(): boolean {
    return false;
  }
}
