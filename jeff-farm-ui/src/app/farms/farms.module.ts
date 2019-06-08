import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { FarmsService } from './farms.service';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: FarmsService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: () => import('../crud/crud.module').then(m => m.CrudModule) }]),
  ],
})
export class FarmsModule { }
