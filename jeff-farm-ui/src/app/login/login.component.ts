import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { AuthService } from '../auth/auth.service';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;
  working: boolean = false;

  constructor(
      private authService: AuthService,
      private loginService: LoginService,
      private router: Router) {

    if (authService.isLoggedIn()) {
      router.navigate(['/user']);
    }
  }

  submitForm() {
    this.working = true;
    this.loginService.login(this.username, this.password)
      .pipe(catchError((error: Error) => {
        this.working = false;
        throw error;
      }))
      .subscribe(_ => this.router.navigate(['/farms']) );
  }
}
