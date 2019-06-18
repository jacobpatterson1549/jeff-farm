import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { CrudChild } from '../crud/crud-child';
import { Hive } from './hive';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class HiveService extends CrudItemService<Hive> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): Hive {
    return new Hive(+this.getParentId());
  }

  getTypeName(): string {
    return 'hive';
  }

  getCrudChildren(): CrudChild[] {
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
