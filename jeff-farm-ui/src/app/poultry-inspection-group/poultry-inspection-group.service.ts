import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemInspectionGroupService } from '../crud/crud-item-inspection-group.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { HeaderItem } from '../header/header-item';
import { Poultry } from '../poultry/poultry';
import { PoultryInspection } from './poultry-inspection';
import { PoultryInspectionGroup } from './poultry-inspection-group';

@Injectable()
export class PoultryInspectionGroupService
  extends CrudItemInspectionGroupService<Poultry, PoultryInspection, PoultryInspectionGroup> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(
      'poultry inspection',
      errorMessagesService,
      http);
  }

  createCrudItem(): PoultryInspectionGroup {
    return new PoultryInspectionGroup(+this.getParentId());
  }

  createCrudItemInspection(): PoultryInspection {
    return new PoultryInspection(+this.getParentId());
  }

  getHeaderItems(): HeaderItem[] {
    return [
      { url: `farm/${this.getParentId()}`, name: 'farm' },
      { url: `farm/${this.getParentId()}/poultry`, name: 'poultry' },
    ];
  }

  protected getBaseUrl(): string {
    return 'poultry/inspectionGroup';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
