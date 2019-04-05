import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService, CrudChild } from '../crud/crud.service';
import { Farm } from './farm';

@Injectable()
export class FarmsService extends CrudService<Farm> {

  constructor(httpClient: HttpClient) {

    super(httpClient);
  }

  createCrudItem(): Farm {
    return new Farm();
  }

  getPluralName(): string {
    return 'Farms';
  }

  getCrudChildren(): CrudChild[] {
    return [
      { pluralName: 'Hives', path: 'hives' },
    ];
  }
  
  getBaseUrl(): string {
    return '/api/farms';
  }
}
