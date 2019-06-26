import { catchError } from 'rxjs/operators';

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../auth/auth.service';
import { LoginService } from '../login/login.service';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Component({
  providers: [UserService],
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  user: User;
  viewUserButtonText: string;
  working = false;

  constructor(
    private authService: AuthService,
    private loginService: LoginService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.userService.get()
      .subscribe(user => {
        this.user = user;
      });
    this.viewUserButtonText = this.authService.isAdminUser() ? 'Users' : 'View Account';
  }

  viewAccount() {
    const viewAccountUrl = this.authService.isAdminUser()
      ? '/user'
      : `/user/${this.authService.getUserId()}`;
    this.router.navigate([viewAccountUrl]);
  }

  logout() {
    this.working = true;
    this.loginService.logout()
      .pipe(catchError((error: Error) => {
        this.working = false;
        throw error;
      }))
      .subscribe(_ => this.router.navigate(['/login']));
  }
}
