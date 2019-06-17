import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserService } from './user.service';
import { CrudModule } from '../crud/crud.module';
import { CrudItemService } from '../crud/crud-item.service';

@NgModule({
  providers: [
    {
      provide: CrudItemService,
      useClass: UserService,
    },
  ],
  imports: [
    CommonModule,
    /*
     * TODO: Admin feature: UserModule should inherit from CrudModule and use that routing (delete UserRoutingModule)
     *       Should not be able to create users there (plumb property through like crudItemService.canUpdate,
     *        should use login module).
     *       Should only be able to see user list if admin user (otherwise, redirect to )
     */
    UserRoutingModule,
    CrudModule,
  ]
})
export class UserModule { }
