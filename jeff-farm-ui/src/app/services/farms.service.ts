import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import { Farm } from '../classes/farm';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class FarmsService extends CrudService<Farm> {

  constructor(
    private httpClient: HttpClient,
    private activatedRoute: ActivatedRoute) {

    super(httpClient, activatedRoute);
  }

  getBaseUrl(): string {
    
    return 'farms';
  }
}
