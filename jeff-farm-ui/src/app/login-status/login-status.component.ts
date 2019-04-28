import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/user/user';
import { UserService } from 'src/app/user/user.service';
import { LoginService } from '../login/login.service';

@Component({
  providers: [UserService],
  selector: 'login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  user: User;

  constructor(
    private authService: AuthService,
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
