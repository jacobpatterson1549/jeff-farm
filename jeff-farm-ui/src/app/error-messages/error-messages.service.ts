import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthService } from '../auth/auth.service';
import { ErrorMessage } from './error-message';

@Injectable({
  providedIn: 'root'
})
export class ErrorMessagesService {

  constructor(
    private authService: AuthService,
    private router: Router) { }

  errorMessages: ErrorMessage[] = [];

  add(message: string) {
    this.errorMessages.push({ date: new Date(), detail: message });
  }

  handleError<T>(attemptedTask: string) {
    return (error: Error): Observable<T> => {
      let errorMessage: string = error.message;

      if (error instanceof HttpErrorResponse) {
        let goToLogin = false;
        if (error.status === 0) {
          errorMessage = 'Server down';
          goToLogin = true;
        } else {
          if (error.status === 403) {
            goToLogin = true;
          }
          errorMessage = error.error;
        }

        if (goToLogin) {
          this.authService.clearCredentials();
          this.router.navigate(['/login']);
        }
      }

      this.add(`${attemptedTask}: ${errorMessage}`);
      throw error;
    };
  }
}
