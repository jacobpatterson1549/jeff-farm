import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminUserGuard implements CanActivate, CanActivateChild {

  constructor(private authService: AuthService, private router: Router) { }

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
    // if (next.url = '/user')
    // TODO:  block if ((url='/user/list' && !this.authService.isAdminUser())
    //                 || (url.startsWith(/user/{id} && (this.authService.isAdminUser() || {id} == this.authService.getUserId())
    return true;
  }
}
