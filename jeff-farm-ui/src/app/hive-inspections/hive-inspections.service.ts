import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService } from '../crud/crud.service';
import { HiveInspection } from './hive-inspection';

@Injectable()
export class HiveInspectionsService extends CrudService<HiveInspection> {

  constructor(httpClient: HttpClient) {

    super(httpClient);
  }

  createCrudItem(): HiveInspection {
    return new HiveInspection(this.getHiveId());
  }

  getChildNames(): string[] {
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