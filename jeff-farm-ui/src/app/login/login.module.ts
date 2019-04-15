import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { LoginRoutingModule } from './login-routing.module';
import { CrudModule } from '../crud/crud.module';
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
    LoginComponent,
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    CrudModule,
  ],
  exports: [
    LoginComponent,
  ],
})
export class LoginModule { }
