import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './auth/auth.guard';
import { PageNotFoundComponent } from './page-not-found.component';
import { AdminUserGuard } from './user/admin-user.guard';

const routes: Routes = [
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
  },
  {
    path: 'user',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule),
    canActivate: [AuthGuard],
    canActivateChild: [AdminUserGuard],
  },
  {
    path: 'farm/:farm_id/poultry/inspection',
    loadChildren: () => import('./poultry-inspection-group/poultry-inspection-group.module')
      .then(m => m.PoultryInspectionGroupModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farm/:farm_id/poultry',
    loadChildren: () => import('./poultry/poultry.module').then(m => m.PoultryModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farm/:farm_id/hive/inspection',
    loadChildren: () => import('./hive-inspection-group/hive-inspection-group.module')
      .then(m => m.HiveInspectionGroupModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farm/:farm_id/hive',
    loadChildren: () => import('./hive/hive.module').then(m => m.HiveModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farm/:farm_id/permission',
    loadChildren: () => import('./farm-permission/farm-permission.module')
      .then(m => m.FarmPermissionModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'farm',
    loadChildren: () => import('./farm/farm.module').then(m => m.FarmModule),
    canActivate: [AuthGuard],
  },
  { path: '', redirectTo: '/farm', pathMatch: 'full' },
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
