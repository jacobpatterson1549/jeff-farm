import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import { AuthService } from '../auth/auth.service';
import { CrudItemService } from '../crud/crud-item.service';
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

  getTypeName(): string {
    return 'user';
  }

  getBaseUrl(): string {
    return this.authService.isLoggedIn() ? 'user' : 'login/create';
  }

  delete(): Observable<object> {

    return super.delete().pipe(tap(_ => this.loginService.logout()));
  }

  get canDeleteMessage(): string { // TODO: get rid of all properties -> they are confusing and slow down the site.
    return 'Cannot delete user because it is the only user with permission to one or more farms.';
  }

  getId(): string {
    return super.getId() || this.authService.getUserId();
  }

  getViewStepsToParent(): number {
    let viewStepsToParent: number = super.getViewStepsToParent();
    if (!this.authService.isAdminUser()) {
      viewStepsToParent++;
    }
    return viewStepsToParent;
  }

  protected getParentId(): string {
    return '';
  }
}
