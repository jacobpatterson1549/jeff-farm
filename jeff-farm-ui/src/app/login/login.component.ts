import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../auth/auth.service';
import { catchError } from 'rxjs/operators';

import { LoginService } from './login.service';
import { environment } from 'src/environments/environment';
import { LoginSuccess } from './login-success';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
      userName: ['', Validators.required],
      password: ['', Validators.required],
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
