import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

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
    return new Poultry(+this.getFarmId());
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

  getFarmId(): string {
    return this.getRouteParam('farm_id');
  }

  protected getListHttpParams(): HttpParams {
    return super.getListHttpParams().append('farmId', this.getFarmId());
  }
}
