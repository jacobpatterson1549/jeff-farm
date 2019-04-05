import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService, CrudChild } from '../crud/crud.service';
import { User } from './user';
import { Observable, of } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class UserService extends CrudService<User> {

  constructor(httpClient: HttpClient, private authService: AuthService) {

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
    return this.authService.isLoggedIn ? '/api/user' : '/api/login/create';
  }

  getList(): Observable<User[]> {
    throw new Error('Not Allowed');
  }

  canDelete(): Observable<boolean> {
    return of(true);
  }
}
