import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService } from '../crud/crud.service';
import { Farm } from './farm';

@Injectable()
export class FarmsService extends CrudService<Farm> {

  constructor(httpClient: HttpClient) {

    super(httpClient);
  }

  createCrudItem(): Farm {
    return new Farm();
  }

  getChildNames(): string[] {
    return ['Hives'];
  }

  getBaseUrl(): string {
    return 'farms';
  }
}
