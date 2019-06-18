import { Injectable } from '@angular/core';
import { LoginSuccess } from '../login/login-success';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly USER_ID_KEY: string = 'isAdminUser';
  private readonly IS_ADMIN_USER_KEY: string = 'isAdminUser';

  getUserId(): number {
    return +localStorage.getItem(this.USER_ID_KEY);
  }

  isAdminUser(): boolean {
    const isAdminUser = localStorage.getItem(this.IS_ADMIN_USER_KEY);
    return isAdminUser === 'true';
  }

  clearCredentials() {
    localStorage.removeItem(this.USER_ID_KEY);
    localStorage.removeItem(this.IS_ADMIN_USER_KEY);
  }

  setLoggedIn(loginSuccess: LoginSuccess) {
    localStorage.setItem(this.USER_ID_KEY, String(loginSuccess.userId));
    localStorage.setItem(this.IS_ADMIN_USER_KEY, String(loginSuccess.isAdminUser));
  }

  isLoggedIn(): boolean {
    return this.getUserId() > 0;
  }
}
