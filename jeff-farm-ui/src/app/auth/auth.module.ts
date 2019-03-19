import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrudModule } from '../crud/crud.module';
import { LoginComponent } from './login/login.component';
import { LoginStatusComponent } from './login-status/login-status.component';
import { LoginRoutingModule } from './login-routing.module';
import { CrudService } from '../crud/crud.service';
import { UserService } from '../user/user.service';

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
