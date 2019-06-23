import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudItemInspectionGroupService } from '../crud/crud-item-inspection-group.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { Hive } from '../hive/hive';
import { HiveInspection } from './hive-inspection';
import { HiveInspectionGroup } from './hive-inspection-group';

@Injectable()
export class HiveInspectionGroupService
  extends CrudItemInspectionGroupService<Hive, HiveInspection, HiveInspectionGroup> {

    constructor(
      errorMessagesService: ErrorMessagesService,
      http: HttpClient) {
      super(errorMessagesService, http);
    }

  createCrudItem(): HiveInspectionGroup {
    return new HiveInspectionGroup(+this.getParentId());
  }

  createCrudItemInspection(): HiveInspection {
    return new HiveInspection(+this.getParentId());
  }

  getTypeName(): string {
    return 'hive inspection group';
  }

  protected getBaseUrl(): string {
    return 'hive/inspectionGroup';
  }

  protected getParentId(): string {
    return this.getRouteParam('farm_id');
  }
}
