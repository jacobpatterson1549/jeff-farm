import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { HiveInspection } from '../classes/hiveInspection';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class HiveInspectionsService extends CrudService<HiveInspection> {

  constructor(private httpClient: HttpClient) {
    super(httpClient, 'hiveINspections');
   }
}
