import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { QueenBee } from '../classes/queenBee';
import { CrudService } from './crud.service';

@Injectable({
  providedIn: 'root'
})
export class QueenBeesService extends CrudService<QueenBee> {

  constructor(private httpClient: HttpClient) {
    super(httpClient, 'queenBees');
   }
}
