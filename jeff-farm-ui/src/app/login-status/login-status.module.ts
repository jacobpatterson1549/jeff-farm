import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { SpinnerModule } from '../spinner/spinner.module';
import { LoginStatusComponent } from './login-status.component';

@NgModule({
  declarations: [
    LoginStatusComponent,
  ],
  imports: [
    CommonModule,
    SpinnerModule,
  ],
  exports: [
    LoginStatusComponent,
  ]
})
export class LoginStatusModule { }
