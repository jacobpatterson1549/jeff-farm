import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from './page-not-found.component';

const routes: Routes = [
  {
    path: 'farms/:farm_id/hives/:hive_id/hiveInspections',
    loadChildren: './hive-inspections/hive-inspections.module#HiveInspectionsModule',
  },
  {
    path: 'farms/:farm_id/hives',
    loadChildren: './hives/hives.module#HivesModule',
  },
  {
    path: 'farms',
    loadChildren: './farms/farms.module#FarmsModule',
  },
  { path: '', redirectTo: '/farms', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes,
      {
        enableTracing: false, // true for debugging purposes only
      })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
