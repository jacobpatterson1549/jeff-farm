import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudItemService } from '../crud/crud.item.service';
import { HiveInspectionsService } from './hive-inspections.service';

@NgModule({
  providers: [
    {
      provide: CrudItemService,
      useClass: HiveInspectionsService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: () => import('../crud/crud.module').then(m => m.CrudModule) }]),
  ],
})
export class HiveInspectionsModule { }
