import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudItemService } from '../crud/crud-item.service';
import { PoultryInspectionGroupService } from './poultry-inspection-group.service';
import { CrudItemGroupService } from '../crud/crud-item-group.service';

@NgModule({
  providers: [
    {
      provide: CrudItemGroupService,
      useClass: PoultryInspectionGroupService
    },
    {
      provide: CrudItemService,
      useClass: PoultryInspectionGroupService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: () => import('../crud/crud.module').then(m => m.CrudModule) }]),
  ],
})
export class PoultryInspectionGroupsModule { }
