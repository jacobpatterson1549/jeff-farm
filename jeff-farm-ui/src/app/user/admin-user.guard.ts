import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, CanActivateChild, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminUserGuard implements CanActivate, CanActivateChild {

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return this.canContinue(next);
  }

  canActivateChild( // TODO: is this used?
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return this.canActivate(next, state);
  }

  canContinue(next: ActivatedRouteSnapshot): boolean {
    if (this.authService.isAdminUser()
      || this.authService.getUserId() === this.route.snapshot.paramMap.get('id')) {
      return true;
    }
  }
}
