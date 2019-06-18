import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudItemService } from '../crud/crud-item.service';
import { FarmPermission } from './farm-permission';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable()
export class FarmPermissionService extends CrudItemService<FarmPermission> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(errorMessagesService, http);
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

  getCanUpdate(): boolean {
    return false;
  }
}
