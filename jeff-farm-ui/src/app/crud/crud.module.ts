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
import { CrudItemViewComponent } from './crud-item-view/crud-item-view.component';
import { CrudItemFormComponent } from './crud-item-form/crud-item-form.component';

@NgModule({
  declarations: [
    NavigationComponent,
    CrudHomeComponent,
    CrudDetailComponent,
    CrudListComponent,
    CrudViewComponent,
    CrudFormComponent,
    CrudDeleteComponent,
    CrudItemViewComponent,
    CrudItemFormComponent,
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
    FormsModule,
    CrudHomeComponent,
  ],
  entryComponents: [
    CrudDeleteComponent,
    CrudItemViewComponent,
    CrudItemFormComponent,
  ]
})
export class CrudModule { }
