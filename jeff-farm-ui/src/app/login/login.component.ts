import { catchError } from 'rxjs/operators';

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

import { environment } from '../../environments/environment';
import { AuthService } from '../auth/auth.service';
import { LoginSuccess } from './login-success';
import { LoginService } from './login.service';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  working = true;
  serverRunning = false;
  showServerConnected = true;
  production = environment.production;

  constructor(
    private fb: FormBuilder,
    private titleService: Title,
    private authService: AuthService,
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
    this.loginForm =  this.fb.group({
      userName: this.fb.control('', Validators.required),
      password: this.fb.control('', Validators.required),
    });
    this.titleService.setTitle('Login');
  }

  submitForm() {
    this.working = true;
    this.loginService.login(this.loginForm.value)
      .pipe(catchError((error: Error) => {
        this.working = false;
        throw error;
      }))
      .subscribe((loginSuccess: LoginSuccess) => {
        this.authService.setLoggedIn(loginSuccess);
        this.router.navigate(['/farm']);
      });
  }
}
