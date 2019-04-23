import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorMessagesService {

  messages: string[] = [];

  add(message: string) {
    this.messages.push(message);
  }

  clear() {
    this.messages = [];
  }

  handleError<T>(attemptedTask: string) {
    return (error: Error): Observable<T> => {
      const errorMessage = (error instanceof HttpErrorResponse)
        ? error.error
        : error.message;
      this.add(`${attemptedTask}: ${errorMessage}`);
      return of();
    };
  }
}
