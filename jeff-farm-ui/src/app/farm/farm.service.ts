import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudChild } from '../crud/crud-child';
import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HeaderItem } from '../header/header-item';
import { Farm } from './farm';

@Injectable()
export class FarmService extends CrudItemService<Farm> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      'farm',
      errorMessagesService,
      http);
  }

  createCrudItem(): Farm {
    return new Farm();
  }

  getHeaderItems(): HeaderItem[] {
    return [];
  }

  getCrudChildren(): CrudChild[] {
    return [
      { name: 'Permissions', path: 'permission' },
      { name: 'Hives', path: 'hive' },
      { name: 'Poultry', path: 'poultry' },
      { name: 'Livestock', path: 'livestock' },
    ];
  }

  protected getBaseUrl(): string {
    return 'farm';
  }

  protected getParentId(): string {
    return '';
  }
}
