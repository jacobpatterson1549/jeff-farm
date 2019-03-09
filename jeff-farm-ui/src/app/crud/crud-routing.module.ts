import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';

import { CrudHomeComponent } from './crud-home/crud-home.component';
import { CrudListComponent } from './crud-list/crud-list.component';
import { CrudFormComponent } from './crud-form/crud-form.component';
import { CrudViewComponent } from './crud-view/crud-view.component';
import { CrudDetailComponent } from './crud-detail/crud-detail.component';


const crudRoutes: Routes = [
    {
        path: '',
        component: CrudHomeComponent,
        children: [
            { path: 'create', component: CrudFormComponent },
            {
                path: ':id',
                component: CrudDetailComponent,
                children: [
                    { path: 'update', component: CrudFormComponent },
                    { path: '', component: CrudViewComponent },
                ],
            },
            { path: '', component: CrudListComponent }
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
