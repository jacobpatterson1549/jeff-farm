import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateChild, RouterStateSnapshot } from '@angular/router';

import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminUserGuard implements CanActivateChild {

  constructor(private authService: AuthService) { }

  canActivateChild(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (state.url === '/user') {
      return this.authService.isAdminUser();
    }

    const id: string = next.paramMap.get('id');
    return id == null // not loaded yet
      || id === this.authService.getUserId()
      || this.authService.isAdminUser();
  }
}
