import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { CrudItemService, CrudChild } from '../crud/crud-item.service';
import { Hive } from './hive';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class HivesService extends CrudItemService<Hive> { // TOOD: rename to HiveService (same for others)

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
      { name: 'Hive Inspections', path: 'hiveInspections' },
    ];
  }

  getBaseUrl(): string {
    return 'hives';
  }

  private getFarmId(): string {
    return localStorage.getItem('farmId');
  }

  protected getListHttpParams(): HttpParams {
    return super.getListHttpParams().append('farmId', this.getFarmId());
  }
}
