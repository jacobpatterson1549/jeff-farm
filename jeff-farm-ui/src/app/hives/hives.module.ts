import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { HivesService } from './hives.service';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: HivesService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: '../crud/crud.module#CrudModule' }]),
  ],
})
export class HivesModule { }
