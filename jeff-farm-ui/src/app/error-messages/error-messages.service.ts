import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorMessagesService {

  constructor (
    private authService: AuthService,
    private router: Router) { }

  messages: string[] = [];

  add(message: string) {
    this.messages.push(message);
  }

  clear() {
    this.messages = [];
  }

  handleError<T>(attemptedTask: string) {
      return (error: Error): Observable<T> => {
        if (error instanceof HttpErrorResponse
          && error.status == 403) { // FORBIDDEN

          console.log('redirecting to login');

          this.authService.clearCredentials();
          this.router.navigate(['/login']);
      }
      else {
      const errorMessage = (error instanceof HttpErrorResponse)
        ? error.error
        : error.message;
      this.add(`${attemptedTask}: ${errorMessage}`);
      }
      return of();
    };
  }
}
