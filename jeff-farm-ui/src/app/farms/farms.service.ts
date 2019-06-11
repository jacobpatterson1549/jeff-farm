import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudItemService, CrudChild } from '../crud/crud.item.service';
import { Farm } from './farm';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class FarmsService extends CrudItemService<Farm> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
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
      { pluralName: 'Poultry', path: 'poultry' },
    ];
  }

  getBaseUrl(): string {
    return 'farms';
  }
}
