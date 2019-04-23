import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { ErrorMessagesService } from './error-messages.service';

@Injectable()
export class ErrorMessagesHandler implements ErrorHandler {

    constructor(private  errorMessagesService: ErrorMessagesService) { }

    handleError(error: Error): void {
        this.errorMessagesService.add(error.message);
    }
}