import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudItemService } from '../crud/crud-item.service';
import { CrudModule } from '../crud/crud.module';
import { PoultryService } from './poultry.service';

@NgModule({
  providers: [
    {
      provide: CrudItemService,
      useClass: PoultryService
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
export class PoultryModule { }
