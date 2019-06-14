import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { CrudItemService, CrudChild } from '../crud/crud-item.service';
import { Farm } from './farm';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class FarmService extends CrudItemService<Farm> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): Farm {
    return new Farm();
  }

  getTypeName(): string {
    return 'farm';
  }

  getCrudChildren(): CrudChild[] {
    return [
      { name: 'Hives', path: 'hive' },
      { name: 'Poultry', path: 'poultry' },
    ];
  }

  getBaseUrl(): string {
    return 'farm';
  }
}
