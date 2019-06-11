import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserService } from './user.service';
import { CrudModule } from '../crud/crud.module';
import { CrudItemService } from '../crud/crud.item.service';

@NgModule({
  providers: [
    {
      provide: CrudItemService,
      useClass: UserService,
    },
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    CrudModule,
  ]
})
export class UserModule { }
