import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { AuthService } from '../auth/auth.service';
import { catchError } from 'rxjs/operators';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  working: boolean = false;

  constructor(
      private titleService: Title,
      private authService: AuthService,
      private loginService: LoginService,
      private router: Router) {

    if (authService.isLoggedIn()) {
      router.navigate(['/user']);
    }
  }

  ngOnInit() {
    this.titleService.setTitle('Login');
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
