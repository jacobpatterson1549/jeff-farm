import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { FarmPermission } from './farm-permission';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class FarmPermissionService extends CrudItemService<FarmPermission> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): FarmPermission {
    return new FarmPermission(+this.getFarmId());
  }

  getTypeName(): string {
    return 'farm permission';
  }

   getBaseUrl(): string {
    return 'farm/permission';
  }

  private getFarmId(): string {
    return localStorage.getItem('farmId');
  }

  protected getListHttpParams(): HttpParams {
    return super.getListHttpParams().append('farmId', this.getFarmId());
  }

  get canUpdate(): boolean {
    return false;
  }
}
