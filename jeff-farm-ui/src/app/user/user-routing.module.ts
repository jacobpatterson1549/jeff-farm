import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';

import { CrudHomeComponent } from '../crud/crud-home/crud-home.component';
import { CrudFormComponent } from '../crud/crud-form/crud-form.component';
import { CrudViewComponent } from '../crud/crud-view/crud-view.component';
import { AuthGuard } from '../auth/auth.guard';

const routes: Routes = [
    {
        path: '',
        children: [
            { path: 'create', component: CrudFormComponent },
            {
                path: '',
                component: CrudHomeComponent,
                children: [
                    { path: 'update', component: CrudFormComponent },
                    { path: '', component: CrudViewComponent },
                ],
                canActivateChild: [AuthGuard],
            },
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
export class UserRoutingModule { }
