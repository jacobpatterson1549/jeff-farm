import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Farm } from '../classes/farm';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class FarmService extends CrudService<Farm> {

  constructor(private httpClient: HttpClient) {
    super(httpClient, 'farms');
   }
}
