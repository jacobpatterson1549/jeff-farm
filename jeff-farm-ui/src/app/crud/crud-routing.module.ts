import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CrudDetailComponent } from './crud-detail/crud-detail.component';
import { CrudFormComponent } from './crud-form/crud-form.component';
import { CrudHomeComponent } from './crud-home/crud-home.component';
import { CrudListComponent } from './crud-list/crud-list.component';
import { CrudViewComponent } from './crud-view/crud-view.component';

const routes: Routes = [
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
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ],
})
export class CrudRoutingModule { }
