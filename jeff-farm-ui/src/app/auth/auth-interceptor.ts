import { Observable } from 'rxjs';

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return this.authService.isLoggedIn()
            ? next.handle(req.clone({
                withCredentials: true,
                setHeaders: {
                    'javax.servlet.request.ssl_session_id': this.authService.getJsessionId(),
                },
            }))
            : next.handle(req);
    }
}
