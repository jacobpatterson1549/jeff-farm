import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { HiveInspection } from './hive-inspection';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class HiveInspectionService extends CrudItemService<HiveInspection> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): HiveInspection {
    return new HiveInspection(+this.getHiveId());
  }

  getTypeName(): string {
    return 'hive inspections';
  }

  protected getBaseUrl(): string {
    return 'hives/inspection';
  }

  private getHiveId(): string {
    return localStorage.getItem('hiveId');
  }

  protected getListHttpParams(): HttpParams {
    return super.getListHttpParams().append('hiveId', this.getHiveId());
  }
}
