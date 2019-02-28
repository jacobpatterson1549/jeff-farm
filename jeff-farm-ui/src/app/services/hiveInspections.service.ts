import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import { HiveInspection } from '../classes/hiveInspection';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class HiveInspectionsService extends CrudService<HiveInspection> {

  constructor(
    private httpClient: HttpClient,
    private route: ActivatedRoute) {
      
    super(httpClient);
  }

  getBaseUrl(): string {

    const farmId: string = this.route.snapshot.paramMap.get('farm_id');
    const hiveId: string = this.route.snapshot.paramMap.get('hive_id');
    return `farms/${farmId}/hives/${hiveId}/hiveInspections`;
  }
}
