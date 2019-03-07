import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService } from '../crud/crud.service';
import { NavigationService } from '../navigation.service';
import { HiveInspection } from './hive-inspection';

@Injectable()
export class HiveInspectionsService extends CrudService<HiveInspection> {

  constructor(
    private navigationService1: NavigationService,
    private httpClient: HttpClient) {

    super(
      navigationService1,
      httpClient);
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

    return this.navigationService1.getRouteParam('farm_id');
  }

  getHiveId(): number {

    return this.navigationService1.getRouteParam('hive_id');
  }
}
