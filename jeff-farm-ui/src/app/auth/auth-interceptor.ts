import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { tap } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let authReq: HttpRequest<any> = req.clone({
            withCredentials: true
        })

        if (this.authService.isLoggedIn) {

            const sessionId: string = this.authService.sessionId;

            authReq = authReq.clone({
                    setHeaders: { 'set-cookie': `JSESSIONID=${sessionId}; Path=/jeff-farm-ws; HttpOnly`}
            });
        }
        return next.handle(authReq);
    }
}