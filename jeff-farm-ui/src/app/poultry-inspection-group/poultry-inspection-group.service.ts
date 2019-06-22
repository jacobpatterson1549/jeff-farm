import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemInspectionGroupService } from '../crud/crud-item-inspection-group.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { Poultry } from '../poultry/poultry';
import { PoultryInspection } from './poultry-inspection';
import { PoultryInspectionGroup } from './poultry-inspection-group';

@Injectable()
export class PoultryInspectionGroupService
  extends CrudItemInspectionGroupService<Poultry, PoultryInspection, PoultryInspectionGroup> {

    constructor(
      errorMessagesService: ErrorMessagesService,
      http: HttpClient) {
      super(errorMessagesService, http);
    }

  createCrudItem(): PoultryInspectionGroup {
    return new PoultryInspectionGroup(+this.getParentId());
  }

  createCrudItemInspection(): PoultryInspection {
    return new PoultryInspection(+this.getParentId());
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
