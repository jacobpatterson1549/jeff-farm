import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserService } from './user.service';
import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { GlobalProviders } from '../global-providers';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: UserService,
    },
    GlobalProviders,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    CrudModule,
  ]
})
export class UserModule { }
