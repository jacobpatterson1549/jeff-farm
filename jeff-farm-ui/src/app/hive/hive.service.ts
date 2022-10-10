import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudChild } from '../crud/crud-child';
import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HeaderItem } from '../header/header-item';
import { Hive } from './hive';

@Injectable()
export class HiveService extends CrudItemService<Hive> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      'hive',
      errorMessagesService,
      http);
  }

  createCrudItem(): Hive {
    return new Hive(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    return [
      { url: `farm/${this.getParentId()}`, name: 'farm' },
    ];
  }


  getCrudGroups(): CrudChild[] {
    return [
      { name: 'Inspection', path: 'inspection' },
    ];
  }

  protected getBaseUrl(): string {
    return 'hive';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
