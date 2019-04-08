import { Injectable, OnInit, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService, CrudChild } from '../crud/crud.service';
import { User } from './user';
import { Observable, of } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class UserService extends CrudService<User> implements OnInit {

  // private authService: AuthService

  constructor(httpClient: HttpClient, private authService: AuthService) {

    super(httpClient);
  }

  ngOnInit(): void {
    // this.authService = this.injector.get(AuthService);
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
    return this.authService.isLoggedIn ? 'user' : 'login/create';
  }

  getList(): Observable<User[]> {
    throw new Error('Not Allowed');
  }

  canDelete(): Observable<boolean> {
    return of(true);
  }
}
