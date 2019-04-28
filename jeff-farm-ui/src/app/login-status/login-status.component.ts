import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../user/user';
import { UserService } from '../user/user.service';
import { LoginService } from '../login/login.service';
import { AuthService } from '../auth/auth.service';

@Component({
  providers: [UserService],
  selector: 'login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  user: User;

  constructor(
    private loginService: LoginService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.userService.get()
      .subscribe(user => {
        this.user = user
      });
  }

  viewAccount() {
    this.router.navigate([`/user`]);
  }

  logout() {
    this.loginService.logout()
      .subscribe(_ => this.router.navigate(['/login']));
  }
}
