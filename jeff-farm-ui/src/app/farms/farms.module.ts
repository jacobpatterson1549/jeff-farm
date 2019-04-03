import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { FarmsService } from './farms.service';
import { GlobalProviders } from '../global-providers';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: FarmsService
    },
    GlobalProviders
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([ { path: '', loadChildren: '../crud/crud.module#CrudModule' } ]),
  ],
})
export class FarmsModule { }
