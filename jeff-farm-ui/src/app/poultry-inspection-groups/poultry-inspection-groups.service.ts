import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudChild } from '../crud/crud.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { PoultryInspectionGroup } from './poultry-inspection-group';
import { PoultryInspection } from './poultry-inspection';
import { CrudItemGroupsService } from '../crud/crud-item-group.service';
import { Poultry } from '../poultry/poultry';
import { PoultryService } from '../poultry/poultry.service';

@Injectable()
export class PoultryInspectionGroupsService
  extends CrudItemGroupsService<Poultry, PoultryInspection, PoultryInspectionGroup> {

  constructor(
    poultryService: PoultryService,
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(poultryService, errorsService, httpClient);
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
