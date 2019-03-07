import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { NavigationService } from '../navigation.service';
import { CrudService } from '../crud/crud.service';
import { Hive } from './hive';

@Injectable()
export class HivesService extends CrudService<Hive> {

  constructor(
    private navigationService1: NavigationService,
    private httpClient: HttpClient) {
      
    super(
      navigationService1,
      httpClient);
  }

  createCrudItem(): Hive {
    return new Hive(this.getFarmId());
  }

  getChildNames(): string[] {
    return ['HiveInspections'];
  }

  getBaseUrl(): string {

    return `farms/${this.getFarmId()}/hives`;
  }

  getFarmId(): number {

    return this.navigationService1.getRouteParam('farm_id');
  }
}
