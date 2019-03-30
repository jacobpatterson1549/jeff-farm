import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from '../user/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  isLoggedIn: boolean = false;
  sessionId: string;
  private readonly SESSION_ID_KEY : string = 'JSESSIONID';

  constructor(private httpClient: HttpClient) {

    const savedSessionId: string = localStorage.getItem(this.SESSION_ID_KEY);

    if (savedSessionId != null && savedSessionId.length > 0) {

      this.isLoggedIn = true;
      this.sessionId = savedSessionId;
    }
  }

  login(username: string, password: string): Observable<string> {

    const user: User = new User();
    user.userName = username;
    user.password = password;

    return this.httpClient.post<string>('login', user, httpOptions)
      .pipe(
        catchError((error: HttpErrorResponse)  => {
          return throwError('error');
        }),
        tap((sessionId: string) => {
          this.isLoggedIn = true;
          this.sessionId = sessionId;

          localStorage.setItem(this.SESSION_ID_KEY, sessionId);
        }),
      );
  }

  logout(): Observable<any> {

    this.clearCredentials(); // clear even if logout fails.
    
    return this.isLoggedIn
      ? this.httpClient.get<any>('user/logout')
        .pipe(
          catchError((error: HttpErrorResponse)  => {
            return throwError('error');
          }),
          // tap(this.clearCredentials)
        )
      : of();
  }

  clearCredentials() {
    this.isLoggedIn = false;
    this.sessionId = null;

    localStorage.removeItem(this.SESSION_ID_KEY);
  }
}
