import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudItemMapService } from '../crud/crud-item-map-service';
import { CrudItemService } from '../crud/crud-item.service';
import { CrudModule } from '../crud/crud.module';
import { LivestockMapService } from './livestock-map.service';

@NgModule({
  providers: [
    {
      provide: CrudItemMapService,
      useClass: LivestockMapService
    },
    {
      provide: CrudItemService,
      useClass: LivestockMapService
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
export class LivestockMapModule { }
