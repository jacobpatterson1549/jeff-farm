import { Injectable } from '@angular/core';
import { CachingService } from '../caching.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private readonly IS_LOGGED_IN_KEY : string = 'isLoggedIn';

  constructor(
    private cachingService: CachingService) { }

  clearCredentials() {
    localStorage.setItem(this.IS_LOGGED_IN_KEY, 'false');

    this.cachingService.clear();
  }

  setLoggedIn() {
    localStorage.setItem(this.IS_LOGGED_IN_KEY, 'true');
  }

  isLoggedIn(): boolean {
    const isLoggedIn = localStorage.getItem(this.IS_LOGGED_IN_KEY);
    return isLoggedIn == 'true';
  }
}
