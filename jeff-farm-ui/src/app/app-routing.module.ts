import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';

import { PageNotFoundComponent } from './page-not-found.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
  },
  {
    path: 'user',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/poultry/inspections',
    loadChildren: () => import('./poultry-inspection-groups/poultry-inspection-groups.module').then(m => m.PoultryInspectionGroupsModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/poultry',
    loadChildren: () => import('./poultry/poultry.module').then(m => m.PoultryModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/hives/:hive_id/hiveInspections',
    loadChildren: () => import('./hive-inspections/hive-inspections.module').then(m => m.HiveInspectionsModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farms/:farm_id/hives',
    loadChildren: () => import('./hives/hives.module').then(m => m.HivesModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farms',
    loadChildren: () => import('./farms/farms.module').then(m => m.FarmsModule),
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
