import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { AuthService } from '../auth/auth.service';
import { CrudItemService, CrudChild } from '../crud/crud.item.service';
import { User } from './user';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { LoginService } from '../login/login.service';

@Injectable()
export class UserService extends CrudItemService<User> {

  constructor(
    private authService: AuthService,
    private loginService: LoginService,
    errorsService: ErrorMessagesService,
    httpClient: HttpClient) {

    super(errorsService, httpClient);
  }

  createCrudItem(): User {
    return new User();
  }

  getPluralName(): string {
    return 'Users';
  }

  getBaseUrl(): string {
    return this.authService.isLoggedIn() ? 'user' : 'login/create';
  }

  getList(): Observable<User[]> {
    throw new Error('Not Allowed');
  }

  delete(): Observable<object> {

    return super.delete().pipe(tap(_ => this.loginService.logout()));
  }

  canDelete(): Observable<boolean> {
    return of(true);
  }
}
