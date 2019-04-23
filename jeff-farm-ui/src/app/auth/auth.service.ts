import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from '../user/user';
import { CachingService } from '../caching.service';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  isLoggedIn: boolean = false;
  jsessionid: string = null;
  private readonly IS_LOGGED_IN_KEY : string = 'isLoggedIn';

  constructor(
    private cachingService: CachingService,
    private errorMessagesService: ErrorMessagesService,
    private httpClient: HttpClient) {

    const wasLoggedIn: string = localStorage.getItem(this.IS_LOGGED_IN_KEY);

    if (wasLoggedIn != null && "true" == (wasLoggedIn)) {

      this.isLoggedIn = true;
    }
  }

  login(username: string, password: string): Observable<string> {

    const user: User = new User();
    user.userName = username;
    user.password = password;

    return this.httpClient.post<string>('login', user)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('login')),
        tap(jsessionid => {
          this.isLoggedIn = true;
          this.jsessionid = jsessionid;

          localStorage.setItem(this.IS_LOGGED_IN_KEY, "true");
        }),
      );
  }

  logout(): Observable<any> {

    this.clearCredentials(); // clear even if logout fails.
    
    return this.httpClient.get<any>('user/logout')
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('logout')),
      );
  }

  clearCredentials() {
    this.isLoggedIn = false;

    localStorage.removeItem(this.IS_LOGGED_IN_KEY);

    this.cachingService.clear();
  }
}
