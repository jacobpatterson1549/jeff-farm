import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { CrudChild } from "../crud/crud-child";
import { Hive } from './hive';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class HiveService extends CrudItemService<Hive> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): Hive {
    return new Hive(+this.getFarmId());
  }

  getTypeName(): string {
    return 'hive';
  }

  getCrudChildren(): CrudChild[] {
    return [
      { name: 'Inspection', path: 'inspection' },
    ];
  }

  getBaseUrl(): string {
    return 'hive';
  }

  private getFarmId(): string {
    return localStorage.getItem('farmId');
  }

  protected getListHttpParams(): HttpParams {
    return super.getListHttpParams().append('farmId', this.getFarmId());
  }
}
