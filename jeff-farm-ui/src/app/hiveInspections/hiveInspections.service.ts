import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import { CrudService } from '../crud/crud.service';
import { HiveInspection } from './hiveInspection';
import { HiveInspectionsModule } from './hiveInspections.module';

@Injectable({
  providedIn: HiveInspectionsModule
})
export class HiveInspectionsService extends CrudService<HiveInspection> {

  constructor(
    private httpClient: HttpClient,
    private activatedRoute: ActivatedRoute) {

    super(httpClient, activatedRoute);
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

    return this.getRouteParam('farm_id');
  }

  getHiveId(): number {

    return this.getRouteParam('hive_id');
  }
}
