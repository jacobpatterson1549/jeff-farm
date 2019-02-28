import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Farm } from '../classes/farm';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class FarmsService extends CrudService<Farm> {

  constructor(private httpClient: HttpClient) {

    super(httpClient);
  }

  getBaseUrl(): string {
    
    return 'farms';
  }
}
