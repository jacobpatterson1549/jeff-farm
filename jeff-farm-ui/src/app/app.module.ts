import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './components/page-not-found.component';
import { CrudListComponent } from './components/crud-list/crud-list.component';
import { CrudFormComponent } from './components/crud-form/crud-form.component';
import { FarmsListComponent, FarmsCreateComponent, FarmsUpdateComponent } from './components/farms.component';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    CrudListComponent,
    CrudFormComponent,
    FarmsListComponent,
    FarmsCreateComponent,
    FarmsUpdateComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
