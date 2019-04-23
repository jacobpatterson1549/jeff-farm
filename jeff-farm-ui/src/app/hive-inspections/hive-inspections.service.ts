import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService, CrudChild } from '../crud/crud.service';
import { HiveInspection } from './hive-inspection';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class HiveInspectionsService extends CrudService<HiveInspection> {

  constructor(
    private errorsService: ErrorMessagesService, 
    private httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): HiveInspection {
    return new HiveInspection(this.getHiveId());
  }

  getPluralName(): string {
    return 'Hive Inspections';
  }

  getCrudChildren(): CrudChild[] {
    return [];
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
