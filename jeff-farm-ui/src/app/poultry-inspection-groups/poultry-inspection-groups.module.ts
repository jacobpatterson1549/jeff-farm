import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudItemService } from '../crud/crud.item.service';
import { PoultryInspectionGroupsService } from './poultry-inspection-groups.service';
import { CrudItemGroupsService } from '../crud/crud-item-group.service';

@NgModule({
  providers: [
    {
      provide: CrudItemGroupsService,
      useClass: PoultryInspectionGroupsService
    },
    {
      provide: CrudItemService,
      useClass: PoultryInspectionGroupsService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: () => import('../crud/crud.module').then(m => m.CrudModule) }]),
  ],
})
export class PoultryInspectionGroupsModule { }
