import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import { CrudService } from '../crud/crud.service';
import { Farm } from './farm';
import { FarmsModule } from './farms.module';

@Injectable()
export class FarmsService extends CrudService<Farm> {

  constructor(
    private httpClient: HttpClient,
    private activatedRoute: ActivatedRoute) {

    super(httpClient, activatedRoute);
  }

  createCrudItem(): Farm {
    return new Farm();
  }

  getChildNames(): string[] {
    return ['Hives'];
  }

  getBaseUrl(): string {
    return 'farms';
  }
}
