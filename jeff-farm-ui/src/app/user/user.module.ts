import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserService } from './user.service';
import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: UserService,
    },
  ],
  imports: [
    CommonModule,
    CrudModule,
    UserRoutingModule
  ]
})
export class UserModule { }
