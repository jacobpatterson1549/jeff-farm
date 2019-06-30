import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginStatusModule } from '../login-status/login-status.module';
import { SpinnerModule } from '../spinner/spinner.module';
import { CrudChartComponent } from './crud-chart/crud-chart.component';
import { CrudDeleteComponent } from './crud-delete/crud-delete.component';
import { CrudDetailComponent } from './crud-detail/crud-detail.component';
import { CrudFormComponent } from './crud-form/crud-form.component';
import { CrudHomeComponent } from './crud-home/crud-home.component';
import { CrudItemInputBooleanComponent } from './crud-item-input/crud-item-input-boolean.component';
import { CrudItemInputColorComponent } from './crud-item-input/crud-item-input-color.component';
import { CrudItemInputIntegerComponent } from './crud-item-input/crud-item-input-integer.component';
import {
    CrudItemInputPasswordComponent
} from './crud-item-input/crud-item-input-password.component';
import { CrudItemInputStarsComponent } from './crud-item-input/crud-item-input-stars.component';
import { CrudItemInputStringComponent } from './crud-item-input/crud-item-input-string.component';
import {
    CrudItemInputTextAreaComponent
} from './crud-item-input/crud-item-input-text-area.component';
import { CrudItemInputComponent } from './crud-item-input/crud-item-input.component';
import {
    CrudItemInspectionGroupInputComponent
} from './crud-item-inspection-group-input/crud-item-inspection-group-input.component';
import { CrudItemInspectionGroupGuard } from './crud-item-inspection-group.guard';
import { CrudItemMapInputComponent } from './crud-item-map-input/crud-item-map-input.component';
import { CrudListComponent } from './crud-list/crud-list.component';
import { CrudRoutingModule } from './crud-routing.module';
import { CrudViewComponent } from './crud-view/crud-view.component';
import { NavigationComponent } from './navigation.component';
import { CrudChart2Component } from './crud-chart2/crud-chart2.component';

@NgModule({
  providers: [
    CrudItemInspectionGroupGuard,
  ],
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
    CrudItemMapInputComponent,
    CrudItemInputStringComponent,
    CrudItemInputBooleanComponent,
    CrudItemInputIntegerComponent,
    CrudItemInputStarsComponent,
    CrudItemInputColorComponent,
    CrudItemInputPasswordComponent,
    CrudItemInputTextAreaComponent,
    CrudChartComponent,
    CrudChart2Component,
  ],
  imports: [
    CommonModule,
    NgbModule, // for delete modal
    ReactiveFormsModule,
    CrudRoutingModule,
    LoginStatusModule,
    SpinnerModule,
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    SpinnerModule,
    NavigationComponent,
    CrudHomeComponent,
    CrudItemInputComponent,
  ],
  entryComponents: [
    CrudDeleteComponent,
    CrudItemInputComponent,
  ]
})
export class CrudModule { }
