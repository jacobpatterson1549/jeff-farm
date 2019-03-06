import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrudModule } from '../crud/crud.module';
import { CrudService } from '../crud/crud.service';
import { HivesService } from './hives.service';
import { HIVES_ROUTES } from '../routes';

@NgModule({
  providers: [
    {
      provide: CrudService,
      useClass: HivesService
    },
  ],
  imports: [
    CrudModule,
    RouterModule.forChild(HIVES_ROUTES),
  ]
})
export class HivesModule { }
