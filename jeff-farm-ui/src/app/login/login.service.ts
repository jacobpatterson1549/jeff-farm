import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { User } from '../user/user';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private authService: AuthService,
    private errorMessagesService: ErrorMessagesService,
    private httpClient: HttpClient) { }

  login(username: string, password: string): Observable<string> {
    const user: User = new User();
    user.userName = username;
    user.password = password;

    return this.httpClient.post<string>('login', user)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('login')),
        tap(_ => this.authService.setLoggedIn()),
      );
  }

  logout(): Observable<any> {
    this.authService.clearCredentials(); // clear even if logout fails.
    return this.httpClient.get<any>('user/logout')
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('logout')),
      );
  }

  serverRunning(): Observable<boolean> {
    return this.httpClient.get<boolean>('login/status')
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('login-status')),
        map(_ => true),
      );
  }
}
