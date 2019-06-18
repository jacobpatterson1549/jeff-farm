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
    return new FarmPermission(+this.getParentId());
  }

  getTypeName(): string {
    return 'farm permission';
  }

  protected getBaseUrl(): string {
    return 'farm/permission';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }

  get canUpdate(): boolean {
    return false;
  }
}
