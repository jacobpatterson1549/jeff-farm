import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemService } from '../crud/crud-item.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HeaderItem } from '../header/header-item';
import { FarmPermission } from './farm-permission';

@Injectable()
export class FarmPermissionService extends CrudItemService<FarmPermission> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      'farm permission',
      errorMessagesService,
      http);
  }

  createCrudItem(): FarmPermission {
    return new FarmPermission(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    return [
      { url: `farm/${this.getParentId()}`, name: 'farm' },
    ];
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
