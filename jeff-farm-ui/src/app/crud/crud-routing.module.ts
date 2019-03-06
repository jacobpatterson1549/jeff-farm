import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';

import { CrudHomeComponent } from './crud-home/crud-home.component';
import { CrudListComponent } from './crud-list/crud-list.component';
import { CrudFormComponent } from './crud-form/crud-form.component';
import { CrudViewComponent } from './crud-view/crud-view.component';


const crudRoutes: Routes = [
    {
        path: '',
        component: CrudHomeComponent,
        children: [
            { path: ':id/update', component: CrudFormComponent },
            { path: ':id',        component: CrudViewComponent }, // TODO: Combine the two :id components into a CrudItemComonent (display the displayName of the the fetched crudItem)
            { path: 'create',     component: CrudFormComponent },
            { path: '',           component: CrudListComponent }
        ],
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(crudRoutes)
    ],
    exports: [
        RouterModule
    ],
})
export class CrudRoutingModule { }
