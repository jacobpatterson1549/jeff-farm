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

    // TODO: Get userId (from localstorage?)
    const id: number = 1;
    this.router.navigate([`/user/${id}`]);
  }

  logout() {

    this.authService.logout()
    this.router.navigate(['/login']);
  }

  isOnLoginPage() {

    return this.router.url == '/login';
  }
}
