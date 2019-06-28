import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemMapService } from '../crud/crud-item-map-service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HeaderItem } from '../header/header-item';

@Injectable()
export class LivestockMapService extends CrudItemMapService {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      'livestock map',
      errorMessagesService,
      http);
  }


  getHeaderItems(): HeaderItem[] {
    return [
      { url: `farm/${this.getParentId()}`, name: 'farm' },
      { url: `farm/${this.getParentId()}/livestock`, name: 'livestock' },
    ];
  }

  protected getBaseUrl(): string {
    return 'livestock/map';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
