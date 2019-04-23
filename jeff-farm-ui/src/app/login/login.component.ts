import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;

  constructor(
      private authService: AuthService,
      private loginService: LoginService,
      private router: Router) {

    if (authService.isLoggedIn()) {
      router.navigate(['/user']);
    }
  }

  submitForm() {
    this.loginService.login(this.username, this.password)
      .subscribe(_ => this.router.navigate(['/farms']) );
  }
}
