import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './components/page-not-found.component';
import { CrudListComponent } from './components/crud-list/crud-list.component';
import { CrudViewComponent } from './components/crud-view/crud-view.component';
import { CrudFormComponent } from './components/crud-form/crud-form.component';
import { FarmsListComponent, FarmsViewComponent, FarmsCreateComponent, FarmsUpdateComponent } from './components/farms.component';
import { HivesListComponent, HivesViewComponent, HivesCreateComponent, HivesUpdateComponent } from './components/hives.component';
import { QueenBeesListComponent, QueenBeesViewComponent, QueenBeesCreateComponent, QueenBeesUpdateComponent } from './components/queen-bees.component';
import { HiveInspectionsListComponent, HiveInspectionsViewComponent, HiveInspectionsCreateComponent, HiveInspectionsUpdateComponent } from './components/hive-inspections.component';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    CrudListComponent,
    CrudViewComponent,
    CrudFormComponent,
    FarmsListComponent, FarmsViewComponent, FarmsCreateComponent, FarmsUpdateComponent,
    HivesListComponent, HivesViewComponent, HivesCreateComponent, HivesUpdateComponent,
    QueenBeesListComponent, QueenBeesViewComponent, QueenBeesCreateComponent, QueenBeesUpdateComponent,
    HiveInspectionsListComponent, HiveInspectionsViewComponent, HiveInspectionsCreateComponent, HiveInspectionsUpdateComponent,
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
