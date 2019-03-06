import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudModule } from '../crud/crud.module';
import { HIVE_INSPECTIONS_ROUTES } from '../routes';

@NgModule({
  imports: [
    CrudModule,
    RouterModule.forChild(HIVE_INSPECTIONS_ROUTES),
  ]
})
export class HiveInspectionsModule { }
