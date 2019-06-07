import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';

import { PageNotFoundComponent } from './page-not-found.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  {
    path: 'login',
    loadChildren: './login/login.module#LoginModule',
  },
  {
    path: 'user',
    loadChildren: './user/user.module#UserModule',
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/poultry/inspections',
    loadChildren: './poultry-inspection-groups/poultry-inspection-groups.module#PoultryInspectionGroupsModule',
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/poultry',
    loadChildren: './poultry/poultry.module#PoultryModule',
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/hives/:hive_id/hiveInspections',
    loadChildren: './hive-inspections/hive-inspections.module#HiveInspectionsModule',
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/hives',
    loadChildren: './hives/hives.module#HivesModule',
    canActivate: [AuthGuard],
  },
  {
    path: 'farms',
    loadChildren: './farms/farms.module#FarmsModule',
    canActivate: [AuthGuard],
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
        preloadingStrategy: PreloadAllModules,
        paramsInheritanceStrategy: 'always',
      })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
