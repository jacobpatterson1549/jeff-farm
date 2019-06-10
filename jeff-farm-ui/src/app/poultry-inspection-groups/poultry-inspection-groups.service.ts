import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { PoultryInspectionGroup } from './poultry-inspection-group';
import { PoultryInspection } from './poultry-inspection';
import { CrudItemGroupsService } from '../crud/crud-item-group.service';
import { Poultry } from '../poultry/poultry';

@Injectable()
export class PoultryInspectionGroupsService
  extends CrudItemGroupsService<Poultry, PoultryInspection, PoultryInspectionGroup> {

  constructor(
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): PoultryInspectionGroup {
    return new PoultryInspectionGroup();
  }

  getSingularName(): string {
    return 'Poultry Inspection Group';
  }

  getPluralName(): string {
    return 'Poultry Inspection Groups';
  }

  getBaseUrl(): string {

    return `farms/${this.getFarmId()}/poultryInspectionGroups`;
  }

  getFarmId(): number {

    return +this.getRouteParam('farm_id');
  }
}
