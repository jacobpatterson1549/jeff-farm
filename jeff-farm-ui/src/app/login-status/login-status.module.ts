import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { LoginStatusComponent } from './login-status.component';

@NgModule({
  declarations: [
    LoginStatusComponent,
  ],
  imports: [
    CommonModule
  ],
  exports: [
    LoginStatusComponent,
  ]
})
export class LoginStatusModule { }
