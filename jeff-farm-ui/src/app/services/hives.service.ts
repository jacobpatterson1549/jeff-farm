import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import { Hive } from '../classes/hive';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class HivesService extends CrudService<Hive> {

  constructor(
    private httpClient: HttpClient,
    private activatedRoute: ActivatedRoute) {
      
    super(httpClient, activatedRoute);
  }

  getBaseUrl(): string {

    return `farms/${this.getFarmId()}/hives`;
  }

  getFarmId(): number {

    return this.getRouteParam('farm_id');
  }
}
