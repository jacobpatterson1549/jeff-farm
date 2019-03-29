import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './auth/auth.service';

@Injectable()
export class ErrorsHandler implements ErrorHandler {

    constructor(private injector: Injector) { }

    handleError(error: Error): void {
        
        if (error instanceof HttpErrorResponse
            && error.status in [0, 403]) { // unknown, FORBIDDEN

            console.log('redirecting to login');

            const authService: AuthService = this.injector.get(AuthService);
            authService.clearCredentials();
            const router: Router = this.injector.get(Router);
            router.navigate(['/login']);
            return;
        }
        else {
            // alert(error.message);
            console.log(error);
        }
    }
}