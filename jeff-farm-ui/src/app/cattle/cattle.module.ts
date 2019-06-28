import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudItemService } from '../crud/crud-item.service';
import { CrudModule } from '../crud/crud.module';
import { CattleService } from './cattle.service';

@NgModule({
  providers: [
    {
      provide: CrudItemService,
      useClass: CattleService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{
      path: '', loadChildren: () => import('../crud/crud.module')
        .then(m => m.CrudModule)
    }]),
  ],
})
export class CattleModule { }
