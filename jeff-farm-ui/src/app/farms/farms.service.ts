import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService, CrudChild } from '../crud/crud.service';
import { Farm } from './farm';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class FarmsService extends CrudService<Farm> {

  constructor(
    private errorsService: ErrorMessagesService,
    private httpClient: HttpClient) {

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
    ];
  }

  getBaseUrl(): string {
    return 'farms';
  }
}
