import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from './components/page-not-found.component';
import { FarmsListComponent, FarmsViewComponent, FarmsCreateComponent, FarmsUpdateComponent } from './components/farms.component';

const routes: Routes = [
  {
    path: 'farms',
    component: FarmsListComponent
  },
  {
    path: 'farms/create',
    component: FarmsCreateComponent
  },
  {
    path: 'farms/:id',
    component: FarmsViewComponent
  },
  {
    path: 'farms/:id/update',
    component: FarmsUpdateComponent
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
