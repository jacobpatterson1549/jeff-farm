import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { User } from '../user/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  // TODO: combine common url with crud service
  loginUrl: string = 'http://localhost:8080/jeff-farm-ws/login';
  isLoggedIn: boolean = false;
  sessionId: string;

  constructor(private httpClient: HttpClient) {
    // TODO: check to see if user is logged in [and set loginUrl]
  }

  login(username: string, password: string): Observable<string> {

    const user: User = new User();
    user.userName = username;
    user.password = password;
    return this.httpClient.post<string>(this.loginUrl, user, httpOptions)
      .pipe(
        catchError((error: HttpErrorResponse)  => {
          return throwError('error');
        }),
        tap((sessionId: string) => {
          this.isLoggedIn = true;
          this.sessionId = sessionId;
          }), // TODO: ensure logging in with an invalid password does not trigger this
        // TODO: store session id in local storage
        );
  }

  logout(): Observable<any> {

    if (this.isLoggedIn) {

      return this.httpClient.get<any>('http://localhost:8080/jeff-farm-ws/user/logout')
        .pipe(
          catchError((error: HttpErrorResponse)  => {
            return throwError('error');
          }),
          tap(result => {
            this.isLoggedIn = false;
            this.sessionId = null;
            // TODO: invalidate session [and saved session id]
          })
        );
    }

    return of();
  }
}
