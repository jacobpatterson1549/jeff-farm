import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NavigationComponent } from './navigation.component';
import { CrudHomeComponent } from './crud-home/crud-home.component';
import { CrudDetailComponent } from './crud-detail/crud-detail.component';
import { CrudListComponent } from './crud-list/crud-list.component';
import { CrudViewComponent } from './crud-view/crud-view.component';
import { CrudFormComponent } from './crud-form/crud-form.component';
import { CrudRoutingModule } from './crud-routing.module';
import { LoginStatusModule } from '../login-status/login-status.module';
import { CrudDeleteComponent } from './crud-delete/crud-delete.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SpinnerModule } from '../spinner/spinner.module';
import { CrudItemInputComponent } from './crud-item-input/crud-item-input.component';
import { CrudItemInspectionGroupInputComponent } from './crud-item-inspection-group-input/crud-item-inspection-group-input.component';
import { CrudItemInputStringComponent } from './crud-item-input-string.component';
import { CrudItemInputBooleanComponent } from './crud-item-input-boolean.component';
import { CrudItemInputIntegerComponent } from './crud-item-input-integer.component';
import { CrudItemInputStarsComponent } from './crud-item-input-stars.component';
import { CrudItemInputColorComponent } from './crud-item-input-color.component';
import { CrudItemInputPasswordComponent } from './crud-item-input-password.component';
import { CrudItemInputTextAreaComponent } from './crud-item-input-text-area.component';

@NgModule({
  declarations: [
    NavigationComponent,
    CrudHomeComponent,
    CrudDetailComponent,
    CrudListComponent,
    CrudViewComponent,
    CrudFormComponent,
    CrudDeleteComponent,
    CrudItemInputComponent,
    CrudItemInspectionGroupInputComponent,
    CrudItemInputStringComponent,
    CrudItemInputBooleanComponent,
    CrudItemInputIntegerComponent,
    CrudItemInputStarsComponent,
    CrudItemInputColorComponent,
    CrudItemInputPasswordComponent,
    CrudItemInputTextAreaComponent,
  ],
  imports: [
    CommonModule,
    NgbModule, // for delete modal
    FormsModule,
    ReactiveFormsModule,
    CrudRoutingModule,
    LoginStatusModule,
    SpinnerModule,
  ],
  exports: [
    CommonModule,
    CrudHomeComponent,
  ],
  entryComponents: [
    CrudDeleteComponent,
    CrudItemInputComponent,
  ]
})
export class CrudModule { }
