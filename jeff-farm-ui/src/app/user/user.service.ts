import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService, CrudChild } from '../crud/crud.service';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable()
export class UserService extends CrudService<User> {

  constructor(httpClient: HttpClient) {

    super(httpClient);
  }

  createCrudItem(): User {
    return new User();
  }

  getPluralName(): string {
    return 'Users';
  }

  getCrudChildren(): CrudChild[] {
    return [];
  }
  
  getBaseUrl(): string {
    return 'login';
  }

  getList(): Observable<User[]> {
    throw new Error('Not Allowed');
  }
}
