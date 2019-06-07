import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { PoultryInspectionGroupsService } from './poultry-inspection-groups.service';
import { CrudItemGroupsService } from '../crud/crud-item-group.service';
import { PoultryService } from '../poultry/poultry.service';

@NgModule({
  providers: [
    {
      provide: CrudItemGroupsService,
      useClass: PoultryInspectionGroupsService
    },
    {
      provide: CrudService, // TODO: investigate if this can be excluded.
      useClass: PoultryInspectionGroupsService
    },
    {
      provide: PoultryService,
      useClass: PoultryService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: '../crud/crud.module#CrudModule' }]),
  ],
})
export class PoultryInspectionGroupsModule { }
