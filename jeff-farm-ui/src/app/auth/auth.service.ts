import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from '../user/user';

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
    console.log(`isLoggedId=${this.isLoggedIn}  SessionId=${this.sessionId}`);
  }

  login(username: string, password: string): Observable<string> {

    const user: User = new User();
    user.userName = username;
    user.password = password;

    return this.httpClient.post<string>('login', user)
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
    
    return this.httpClient.get<any>('user/logout');
  }

  clearCredentials() {
    this.isLoggedIn = false;
    this.sessionId = null;

    localStorage.removeItem(this.SESSION_ID_KEY);
  }
}
