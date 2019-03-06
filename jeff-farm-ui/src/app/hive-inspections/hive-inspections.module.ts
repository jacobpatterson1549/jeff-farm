import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { HiveInspectionsService } from './hive-inspections.service';
import { HIVE_INSPECTIONS_ROUTES } from '../routes';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: HiveInspectionsService
    },
  ],
  imports: [
    CrudModule,
    RouterModule.forChild(HIVE_INSPECTIONS_ROUTES),
  ]
})
export class HiveInspectionsModule { }
