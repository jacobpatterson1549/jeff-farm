import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { ErrorMessagesService } from './error-messages.service';

@Injectable()
export class ErrorMessagesHandler implements ErrorHandler {

    constructor(
       private injector: Injector, // TODO: use cdi
       private  errorMessagesService: ErrorMessagesService
       ) { }

    handleError(error: Error): void {
        
        if (error instanceof HttpErrorResponse
            && error.status == 403) { // FORBIDDEN

            console.log('redirecting to login');

            const authService: AuthService = this.injector.get(AuthService);
            authService.clearCredentials();
            const router: Router = this.injector.get(Router);
            router.navigate(['/login']);
            return;
        }
        else {
            this.errorMessagesService.add(error.message);
        }
    }
}