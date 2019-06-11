import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudItemService, CrudChild } from '../crud/crud.item.service';
import { HiveInspection } from './hive-inspection';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class HiveInspectionsService extends CrudItemService<HiveInspection> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): HiveInspection {
    return new HiveInspection(this.getHiveId());
  }

  getPluralName(): string {
    return 'Hive Inspections';
  }

  getBaseUrl(): string {

    return `farms/${this.getFarmId()}/hives/${this.getHiveId()}/hiveInspections`;
  }

  getFarmId(): number {

    return +this.getRouteParam('farm_id');
  }

  getHiveId(): number {

    return +this.getRouteParam('hive_id');
  }
}
