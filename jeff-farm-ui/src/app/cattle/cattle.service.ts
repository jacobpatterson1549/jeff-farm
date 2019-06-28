import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudChild } from '../crud/crud-child';
import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HeaderItem } from '../header/header-item';
import { Cattle } from './cattle';

@Injectable()
export class CattleService extends CrudItemService<Cattle> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      'cattle',
      errorMessagesService,
      http);
  }
  createCrudItem(): Cattle {
    return new Cattle(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    return [
      { url: `farm/${this.getParentId()}`, name: 'farm' },
    ];
  }

  getCrudChildren(): CrudChild[] {
    return [
      // { name: 'Map', path: 'map' },
    ];
  }

  protected getBaseUrl(): string {
    return 'cattle';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
