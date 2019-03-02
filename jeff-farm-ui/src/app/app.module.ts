import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './components/page-not-found.component';
import { CrudListComponent } from './components/crud-list/crud-list.component';
import { CrudViewComponent } from './components/crud-view/crud-view.component';
import { CrudFormComponent } from './components/crud-form/crud-form.component';
import { FarmsListComponent, FarmsViewComponent, FarmsCreateComponent, FarmsUpdateComponent } from './components/farms.component';
import { HivesListComponent, HivesViewComponent, HivesCreateComponent, HivesUpdateComponent } from './components/hives.component';
import { HiveInspectionsListComponent, HiveInspectionsViewComponent, HiveInspectionsCreateComponent, HiveInspectionsUpdateComponent } from './components/hive-inspections.component';

const routes: Routes = [
  { path: 'farms', component: FarmsListComponent },
  { path: 'farms/create', component: FarmsCreateComponent },
  { path: 'farms/:id', component: FarmsViewComponent },
  { path: 'farms/:id/update', component: FarmsUpdateComponent },

  { path: 'farms/:farm_id/hives', component: HivesListComponent },
  { path: 'farms/:farm_id/hives/create', component: HivesCreateComponent },
  { path: 'farms/:farm_id/hives/:id', component: HivesViewComponent },
  { path: 'farms/:farm_id/hives/:id/update', component: HivesUpdateComponent },

  { path: 'farms/:farm_id/hives/:hive_id/hiveInspections', component: HiveInspectionsListComponent },
  { path: 'farms/:farm_id/hives/:hive_id/hiveInspections/create', component: HiveInspectionsCreateComponent },
  { path: 'farms/:farm_id/hives/:hive_id/hiveInspections/:id', component: HiveInspectionsViewComponent },
  { path: 'farms/:farm_id/hives/:hive_id/hiveInspections/:id/update', component: HiveInspectionsUpdateComponent },

  { path: '', redirectTo: '/farms', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    CrudListComponent,
    CrudViewComponent,
    CrudFormComponent,
    FarmsListComponent, FarmsViewComponent, FarmsCreateComponent, FarmsUpdateComponent,
    HivesListComponent, HivesViewComponent, HivesCreateComponent, HivesUpdateComponent,
    HiveInspectionsListComponent, HiveInspectionsViewComponent, HiveInspectionsCreateComponent, HiveInspectionsUpdateComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes, { enableTracing: false /* true for debugging purposes only */ })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
