import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { PoultrysService } from './poultry.service';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: PoultrysService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: '../crud/crud.module#CrudModule' }]),
  ],
})
export class PoultryModule { }
