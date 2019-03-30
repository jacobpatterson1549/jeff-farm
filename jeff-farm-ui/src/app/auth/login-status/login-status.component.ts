import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'auth-login-status',
  templateUrl: './login-status.component.html',
})
export class LoginStatusComponent implements OnInit {

  isLoggedIn: boolean = false;
  isOnLoginPage: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router) { }

  ngOnInit() {
    
    this.isLoggedIn = this.authService.isLoggedIn;
    this.isOnLoginPage = this.router.url == '/login';
  }

  viewAccount() {

    this.router.navigate([`/user`]);
  }

  logout() {

  this.authService.logout()
    .subscribe(_ => this.router.navigate(['/login']));
  }
}
