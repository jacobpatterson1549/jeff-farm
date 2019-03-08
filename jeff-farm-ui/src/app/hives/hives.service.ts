import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService } from '../crud/crud.service';
import { Hive } from './hive';

@Injectable()
export class HivesService extends CrudService<Hive> {

  constructor(httpClient: HttpClient) {
      
    super(httpClient);
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

    return +this.getRouteParam('farm_id');
  }
}
