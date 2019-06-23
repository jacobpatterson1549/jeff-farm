import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudItemInspectionGroupService } from '../crud/crud-item-inspection-group.service';
import { CrudItemService } from '../crud/crud-item.service';
import { CrudModule } from '../crud/crud.module';
import { HiveInspectionGroupService } from './hive-inspection-group.service';

@NgModule({
  providers: [
    {
      provide: CrudItemInspectionGroupService,
      useClass: HiveInspectionGroupService
    },
    {
      provide: CrudItemService,
      useClass: HiveInspectionGroupService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{
      path: '', loadChildren: () => import('../crud/crud.module')
        .then(m => m.CrudModule)
    }]),
  ],
})
export class HiveInspectionGroupModule { }
