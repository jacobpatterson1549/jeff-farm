import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { PoultryInspectionGroup } from './poultry-inspection-group';
import { PoultryInspection } from './poultry-inspection';
import { CrudItemGroupService } from '../crud/crud-item-inspection-group.service';
import { Poultry } from '../poultry/poultry';

@Injectable()
export class PoultryInspectionGroupService
  extends CrudItemGroupService<Poultry, PoultryInspection, PoultryInspectionGroup> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
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

  getBaseUrl(): string {
    return 'poultry/inspectionGroup';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
