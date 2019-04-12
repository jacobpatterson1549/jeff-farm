import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from '../user/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  isLoggedIn: boolean = false;
  private readonly IS_LOGGED_IN_KEY : string = 'isLoggedIn';

  constructor(private httpClient: HttpClient) {

    const wasLoggedIn: string = localStorage.getItem(this.IS_LOGGED_IN_KEY);

    if (wasLoggedIn != null && "true" == (wasLoggedIn)) {

      this.isLoggedIn = true;
    }
  }

  login(username: string, password: string): Observable<any> {

    const user: User = new User();
    user.userName = username;
    user.password = password;

    return this.httpClient.post<any>('login', user)
      .pipe(
        catchError((error: HttpErrorResponse)  => {
          return throwError('error');
        }),
        tap(_ => {
          this.isLoggedIn = true;

          localStorage.setItem(this.IS_LOGGED_IN_KEY, "true");
        }),
      );
  }

  logout(): Observable<any> {

    this.clearCredentials(); // clear even if logout fails.
    
    return this.httpClient.get<any>('user/logout');
  }

  clearCredentials() {
    this.isLoggedIn = false;

    localStorage.removeItem(this.IS_LOGGED_IN_KEY);
  }
}
