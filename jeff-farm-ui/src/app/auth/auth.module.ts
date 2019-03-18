import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrudModule } from '../crud/crud.module';
import { LoginComponent } from './login/login.component';
import { LoginStatusComponent } from './login-status/login-status.component';

@NgModule({
  declarations: [
    LoginStatusComponent,
    LoginComponent,
  ],
  imports: [
    CommonModule,
    CrudModule,
  ],
  exports: [
    LoginComponent,
    LoginStatusComponent,
  ]
})
export class AuthModule { }
