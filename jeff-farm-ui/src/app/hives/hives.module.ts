import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudModule } from '../crud/crud.module';
import { HIVES_ROUTES } from '../routes';

@NgModule({
  imports: [
    CrudModule,
    RouterModule.forChild(HIVES_ROUTES),
  ]
})
export class HivesModule { }
