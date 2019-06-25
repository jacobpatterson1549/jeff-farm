import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudChild } from '../crud/crud-child';
import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { FarmService } from '../farm/farm.service';
import { getHeaderItemTypeName, HeaderItem, HeaderItemType } from '../header/header-item';
import { HeaderService } from '../header/header.service';
import { Poultry } from './poultry';

@Injectable()
export class PoultryService extends CrudItemService<Poultry> {

  constructor(
    headerService: HeaderService,
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      HeaderItemType.POULTRY,
      headerService,
      errorMessagesService,
      http);
  }
  createCrudItem(): Poultry {
    return new Poultry(+this.getParentId());
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
  getCrudGroups(): CrudChild[] {
    return [
      { name: 'Inspection', path: 'inspection' },
    ];
  }

  protected getBaseUrl(): string {
    return 'poultry';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
