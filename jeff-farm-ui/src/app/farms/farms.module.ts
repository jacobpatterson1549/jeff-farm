import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { CrudModule } from '../crud/crud.module';

import { FARMS_ROUTES } from '../routes';

@NgModule({
  imports: [
    CrudModule,
    RouterModule.forChild(FARMS_ROUTES),
  ]
})
export class FarmsModule { }
