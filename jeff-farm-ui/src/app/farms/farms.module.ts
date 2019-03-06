import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { FarmsService } from './farms.service';
import { FARMS_ROUTES } from '../routes';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: FarmsService
    },
  ],
  imports: [
    CrudModule,
    RouterModule.forChild(FARMS_ROUTES),
  ]
})
export class FarmsModule { }
