import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudChild } from '../crud/crud-child';
import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HeaderItem } from '../header/header-item';
import { Livestock } from './livestock';

@Injectable()
export class LivestockService extends CrudItemService<Livestock> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      'livestock',
      errorMessagesService,
      http);
  }
  createCrudItem(): Livestock {
    return new Livestock(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    return [
      { url: `farm/${this.getParentId()}`, name: 'farm' },
    ];
  }

  getCrudChildren(): CrudChild[] {
    return [
      { name: 'Map', path: 'map' },
    ];
  }

  protected getBaseUrl(): string {
    return 'livestock';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
