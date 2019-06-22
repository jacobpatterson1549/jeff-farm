import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { CrudItemService } from '../crud/crud-item.service';
import { CrudModule } from '../crud/crud.module';
import { SpinnerModule } from '../spinner/spinner.module';
import { UserService } from '../user/user.service';
import { LoginHomeComponent } from './login-home.component';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';

@NgModule({
  providers: [
    {
      provide: CrudItemService,
      useClass: UserService,
    },
  ],
  declarations: [
    LoginComponent,
    LoginHomeComponent,
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    CrudModule,
    SpinnerModule,
  ],
  exports: [
    LoginComponent,
  ],
})
export class LoginModule { }
