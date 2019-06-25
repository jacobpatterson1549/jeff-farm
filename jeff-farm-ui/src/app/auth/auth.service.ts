import { Injectable } from '@angular/core';

import { CachingService } from '../caching.service';
import { LoginSuccess } from '../login/login-success';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly USER_ID_KEY: string = 'userId';
  private readonly ADMIN_USER_KEY: string = 'isAdminUser';
  private readonly JSESSION_ID_KEY: string = 'jsessionid';

  constructor(private cachingService: CachingService) { }

  getUserId(): string {
    return localStorage.getItem(this.USER_ID_KEY);
  }

  isAdminUser(): boolean {
    return localStorage.getItem(this.ADMIN_USER_KEY) === String(true);
  }

  getJsessionId(): string {
    return localStorage.getItem(this.JSESSION_ID_KEY);
  }

  clearCredentials() {
    localStorage.removeItem(this.USER_ID_KEY);
    localStorage.removeItem(this.ADMIN_USER_KEY);
    localStorage.removeItem(this.JSESSION_ID_KEY);
    this.cachingService.clear();
  }

  setLoggedIn(loginSuccess: LoginSuccess) {
    localStorage.setItem(this.USER_ID_KEY, String(loginSuccess.userId));
    localStorage.setItem(this.ADMIN_USER_KEY, String(loginSuccess.adminUser));
    localStorage.setItem(this.JSESSION_ID_KEY, loginSuccess.jsessionId);
  }

  isLoggedIn(): boolean {
    return +this.getUserId() > 0;
  }
}
