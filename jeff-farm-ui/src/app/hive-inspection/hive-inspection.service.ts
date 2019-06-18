import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { HiveInspection } from './hive-inspection';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class HiveInspectionService extends CrudItemService<HiveInspection> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): HiveInspection {
    return new HiveInspection(+this.getParentId());
  }

  getTypeName(): string {
    return 'hive inspection';
  }

  protected getBaseUrl(): string {
    return 'hive/inspection';
  }

  protected getParentId(): string {
    return this.getRouteParam('hive_id');
  }
}
