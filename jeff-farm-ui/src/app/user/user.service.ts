import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService, CrudChild } from '../crud/crud.service';
import { User } from './user';
import { Observable, of } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { LoginService } from '../login/login.service';

@Injectable()
export class UserService extends CrudService<User> {

  constructor(
    private authService: AuthService,
    private loginService: LoginService,
    private errorsService: ErrorMessagesService,
    private httpClient: HttpClient) {

    super(errorsService, httpClient);
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
    return this.authService.isLoggedIn() ? 'user' : 'login/create';
  }

  getList(): Observable<User[]> {
    throw new Error('Not Allowed');
  }

  delete(): Observable<Object> {

    return super.delete().pipe(tap(_ => this.loginService.logout() ));
  }

  canDelete(): Observable<boolean> {
    return of(true);
  }
}
