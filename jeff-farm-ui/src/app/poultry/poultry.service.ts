import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { CrudChild } from '../crud/crud-child';
import { Poultry } from './poultry';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class PoultryService extends CrudItemService<Poultry> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
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

  getBaseUrl(): string {
    return 'poultry';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
