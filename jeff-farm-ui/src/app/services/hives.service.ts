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
    private route: ActivatedRoute) {
      
    super(httpClient);
  }

  getBaseUrl(): string {

    const farmId: string = this.route.snapshot.paramMap.get('farm_id');
    return `farms/${farmId}/hives`;
  }
}
