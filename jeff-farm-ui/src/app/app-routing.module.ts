import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FarmsComponent } from './components/farms.component';
import { PageNotFoundComponent } from './components/page-not-found.component';

const routes: Routes = [
  {
    path: 'farms',
    component: FarmsComponent
  },
  {
    path: '',
    redirectTo: '/farms',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
