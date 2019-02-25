import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Farm } from '../classes/farm';
import { CrudService } from './crud.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FarmService extends CrudService<Farm> {

  constructor(private httpClient: HttpClient) {
    super(httpClient, 'farms');
   }
}
