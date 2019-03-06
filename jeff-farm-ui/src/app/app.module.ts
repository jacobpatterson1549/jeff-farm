import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found.component';

import { CrudModule } from './crud/crud.module';
import { FarmsModule } from './farms/farms.module';
import { HivesModule } from './hives/hives.module';
import { HiveInspectionsModule } from './hiveInspections/hiveInspections.module';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
  ],
  imports: [
    BrowserModule,
    CrudModule,
    FarmsModule,
    HivesModule,
    HiveInspectionsModule,
    AppRoutingModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
