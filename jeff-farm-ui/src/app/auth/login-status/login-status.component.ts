import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/user/user';
import { UserService } from 'src/app/user/user.service';

@Component({
  providers: [UserService],
  selector: 'auth-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  private user: User;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    // const userService: UserService = this.injector.get(UserService);
    this.userService.get()
      .subscribe(user => {
        this.user = user
      });
  }

  viewAccount() {

    this.router.navigate([`/user`]);
  }

  logout() {

    this.authService.logout()
      .subscribe(_ => this.router.navigate(['/login']));
  }
}
