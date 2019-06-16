import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CrudModule } from '../crud/crud.module';
import { CrudItemService } from '../crud/crud-item.service';
import { FarmPermissionService } from './farm-permission.service';

@NgModule({
  providers: [
    {
      provide: CrudItemService,
      useClass: FarmPermissionService
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    RouterModule.forChild([{ path: '', loadChildren: () => import('../crud/crud.module').then(m => m.CrudModule) }]),
    // TODO: prevent update
    // TODO: routing module like user-routing module?  Params to CrudModule? (userModule has no list/create modes, goes straight to detail)
  ],
})
export class FarmPermissionModule { }
