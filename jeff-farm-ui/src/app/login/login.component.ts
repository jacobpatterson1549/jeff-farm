import { Component } from '@angular/core';
import { Router } from '@angular/router';
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
      private router: Router) {

    if (authService.isLoggedIn) {
      router.navigate(['/user']);
    }
  }

  submitForm() {
    this.authService.login(this.username, this.password)
      .subscribe(_ => this.router.navigate(['/farms']) );
  }
}
