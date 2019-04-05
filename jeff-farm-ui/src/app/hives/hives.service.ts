import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService, CrudChild } from '../crud/crud.service';
import { Hive } from './hive';

@Injectable()
export class HivesService extends CrudService<Hive> {

  constructor(httpClient: HttpClient) {
      
    super(httpClient);
  }

  createCrudItem(): Hive {
    return new Hive(this.getFarmId());
  }

  getPluralName(): string {
    return 'Hives';
  }

  getCrudChildren(): CrudChild[] {
    return [
      { pluralName: 'Hive Inspections', path: 'hiveInspections' },
    ];
  }
  
  getBaseUrl(): string {

    return `/api/farms/${this.getFarmId()}/hives`;
  }

  getFarmId(): number {

    return +this.getRouteParam('farm_id');
  }
}
