import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { CrudItemService, CrudChild } from '../crud/crud-item.service';
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
      { name: 'Inspections', path: 'inspections' },
    ];
  }

  getBaseUrl(): string {
    return 'poultry';
  }

  getFarmId(): string {
    return localStorage.getItem('farmId');
  }

  protected getListHttpParams(): HttpParams {
    return super.getListHttpParams().append('farmId', this.getFarmId());
  }
}
