import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Hive } from '../classes/hive';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class HivesService extends CrudService<Hive> {

  constructor(private httpClient: HttpClient) {
    super(httpClient, 'hives');
   }
}
