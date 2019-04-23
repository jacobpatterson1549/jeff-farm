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
  private readonly JSESSIONID_KEY : string = 'jsessionid';

  constructor(
    private cachingService: CachingService) {

    const wasLoggedIn: string = localStorage.getItem(this.IS_LOGGED_IN_KEY);

    if (wasLoggedIn != null && "true" == (wasLoggedIn)) {

      this.isLoggedIn = true;
      this.jsessionid = localStorage.getItem(this.JSESSIONID_KEY)
    }
  }

  clearCredentials() {
    this.isLoggedIn = false;

    localStorage.removeItem(this.IS_LOGGED_IN_KEY);

    this.cachingService.clear();
  }

  setJSessionId(jsessionid: string) {
    this.isLoggedIn = true;
    this.jsessionid = jsessionid;

    localStorage.setItem(this.IS_LOGGED_IN_KEY, "true");
  }
}
