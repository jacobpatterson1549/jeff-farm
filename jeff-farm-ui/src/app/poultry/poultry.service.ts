import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService, CrudChild } from '../crud/crud.service';
import { Poultry } from './poultry';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class PoultryService extends CrudService<Poultry> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): Poultry {
    return new Poultry(this.getFarmId());
  }

  getSingularName(): string {
    return 'Poultry';
  }

  getPluralName(): string {
    return 'Poultry';
  }

  getCrudGroups(): CrudChild[] {
    return [
      { pluralName: 'Inspections', path: 'inspections' },
    ];
  }

  getBaseUrl(): string {

    return `farms/${this.getFarmId()}/poultry`;
  }

  getFarmId(): number {

    return +this.getRouteParam('farm_id');
  }
}
