import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CrudService } from '../crud/crud.service';
import { NavigationService } from '../navigation.service';
import { Farm } from './farm';

@Injectable()
export class FarmsService extends CrudService<Farm> {

  constructor(
    private navigationService1: NavigationService,
    private httpClient: HttpClient) {

    super(
      navigationService1,
      httpClient);
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
