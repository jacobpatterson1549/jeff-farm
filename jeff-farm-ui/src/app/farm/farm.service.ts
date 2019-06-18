import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { CrudChild } from '../crud/crud-child';
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
      { name: 'Permissions', path: 'permission' },
      { name: 'Hives', path: 'hive' },
      { name: 'Poultry', path: 'poultry' },
    ];
  }

  protected getBaseUrl(): string {
    return 'farm';
  }

  protected getParentId(): string {
    return '';
  }
}
