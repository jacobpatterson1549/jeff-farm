import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from './page-not-found.component';
import { FARMS_ROUTES } from './routes';

const routes: Routes = [
  { path: '', redirectTo: FARMS_ROUTES[0].path, pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes,
      { enableTracing: false /* true for debugging purposes only */ })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
