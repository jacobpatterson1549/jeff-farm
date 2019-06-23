import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HiveInspection } from './hive-inspection';

@Injectable()
export class HiveInspectionService extends CrudItemService<HiveInspection> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(errorMessagesService, http);
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
