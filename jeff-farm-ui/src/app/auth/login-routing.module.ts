import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';

import { CrudFormComponent } from '../crud/crud-form/crud-form.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'create',
                component: CrudFormComponent,
                data: { redirectToParent: true }, // go to parent when creating a user rather than /login/{id}
            },
            { path: '', component: LoginComponent },
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
export class LoginRoutingModule { }
