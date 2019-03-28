import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'auth-login-status',
  templateUrl: './login-status.component.html',
})
export class LoginStatusComponent {

  constructor(
    private authService: AuthService,
    private router: Router) { }

  viewAccount() {

    this.router.navigate([`/user`]);
  }

  logout() {

  this.authService.logout()
    .subscribe(_ => this.router.navigate(['/login']));
  }

  isOnLoginPage() {

    return this.router.url == '/login';
  }
}
