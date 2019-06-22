import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudChild } from '../crud/crud-child';
import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { Poultry } from './poultry';

@Injectable()
export class PoultryService extends CrudItemService<Poultry> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(errorMessagesService, http);
  }

  createCrudItem(): Poultry {
    return new Poultry(+this.getParentId());
  }

  getTypeName(): string {
    return 'poultry';
  }

  getCrudGroups(): CrudChild[] {
    return [
      { name: 'Inspection', path: 'inspection' },
    ];
  }

  protected getBaseUrl(): string {
    return 'poultry';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
