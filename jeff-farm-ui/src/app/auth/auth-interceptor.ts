import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthService } from './auth.service';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (this.authService.isLoggedIn) {

            // const clonedRequest: HttpRequest<any> = req.clone({
            //     headers: req.headers.set('Set-Cookie', `JSESSIONID=${this.authService.sessionId}`),
            //     // headers: req.headers.set('Authorization', this.authService.sessionId),
            //     withCredentials: true,
            // });
            // const clonedRequest = req.clone({ headers: req.headers.set('Set-Cookie', 'jsessionid=' + this.authService.sessionId) });
            // return next.handle(clonedRequest);
            const authToken = this.authService.sessionId;
            const authReq = req.clone({ setHeaders: { Authorization: authToken } });
            return next.handle(authReq);
        }
        else {

            return next.handle(req);
        }
    }
}