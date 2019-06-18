import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { PoultryInspectionGroup } from './poultry-inspection-group';
import { PoultryInspection } from './poultry-inspection';
import { CrudItemGroupService } from '../crud/crud-item-inspection-group.service';
import { Poultry } from '../poultry/poultry';

@Injectable()
export class PoultryInspectionGroupService
  extends CrudItemGroupService<Poultry, PoultryInspection, PoultryInspectionGroup> {

    constructor(
      errorMessagesService: ErrorMessagesService,
      http: HttpClient) {
      super(errorMessagesService, http);
    }

  createCrudItem(): PoultryInspectionGroup {
    return new PoultryInspectionGroup(+this.getParentId());
  }

  createCrudItemInspection(): PoultryInspection {
    return new PoultryInspection();
  }

  getTypeName(): string {
    return 'poultry inspection group';
  }

  protected getBaseUrl(): string {
    return 'poultry/inspectionGroup';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
