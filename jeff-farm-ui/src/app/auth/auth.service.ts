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
  
  private readonly IS_LOGGED_IN_KEY : string = 'isLoggedIn';
  private readonly JSESSIONID_KEY : string = 'jsessionid';

  constructor(
    private cachingService: CachingService) { }

  clearCredentials() {
    localStorage.setItem(this.IS_LOGGED_IN_KEY, 'false');
    localStorage.removeItem(this.JSESSIONID_KEY);

    this.cachingService.clear();
  }

  setJSessionId(jsessionid: string) {
    localStorage.setItem(this.IS_LOGGED_IN_KEY, 'true');
    localStorage.setItem(this.JSESSIONID_KEY, jsessionid);
  }

  isLoggedIn(): boolean {
    const x = localStorage.getItem(this.IS_LOGGED_IN_KEY);
    return x == 'true';
  }

  getJsessionId() {
    return localStorage.getItem(this.JSESSIONID_KEY);
  }
}
