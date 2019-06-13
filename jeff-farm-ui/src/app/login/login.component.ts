import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../auth/auth.service';
import { catchError, timeout } from 'rxjs/operators';

import { LoginService } from './login.service';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  working = true;
  serverRunning = false;
  showServerConnected = true;

  constructor(
    private titleService: Title,
    authService: AuthService,
    private loginService: LoginService,
    private router: Router) {

    if (authService.isLoggedIn()) {
      router.navigate(['/user']);
    }

    loginService.serverRunning()
      .pipe(catchError((error: Error) => {
        this.working = false;
        throw error;
      }))
      .subscribe((serverRunning: boolean) => {
        this.working = false;
        this.serverRunning = serverRunning;
        if (serverRunning) {
          setTimeout(() => {
            this.showServerConnected = false;
          }, 3000);
        }
      });
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
      .subscribe(_ => this.router.navigate(['/farms']));
  }
}
