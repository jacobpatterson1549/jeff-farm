import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { UserService } from '../user/user.service';
import { LoginRoutingModule } from './login-routing.module';
import { LoginStatusComponent } from './login-status/login-status.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: UserService,
    },
  ],
  declarations: [
    LoginStatusComponent,
    LoginComponent,
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    CrudModule,
  ],
  exports: [
    LoginComponent,
    LoginStatusComponent,
  ]
})
export class AuthModule { }
