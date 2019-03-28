import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserService } from './user.service';
import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { httpInterceptorProviders } from '../http-interceptors';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: UserService,
    },
    httpInterceptorProviders
  ],
  imports: [
    CommonModule,
    CrudModule,
    UserRoutingModule
  ]
})
export class UserModule { }
