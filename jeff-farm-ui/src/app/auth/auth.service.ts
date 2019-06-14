import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly IS_LOGGED_IN_KEY: string = 'isLoggedIn';

  clearCredentials() {
    localStorage.setItem(this.IS_LOGGED_IN_KEY, 'false');
  }

  setLoggedIn() {
    localStorage.setItem(this.IS_LOGGED_IN_KEY, 'true');
  }

  isLoggedIn(): boolean {
    const isLoggedIn = localStorage.getItem(this.IS_LOGGED_IN_KEY);
    return isLoggedIn === 'true';
  }
}
