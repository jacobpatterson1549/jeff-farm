import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // if (this.authService.isLoggedIn()) {
        //     const jsessionId: string = this.authService.getJsessionId();
        //     req = req.clone({
        //         // setHeaders: {'Set-Cookie': `JSESSIONID=${jsessionId}`},
        //         setHeaders: {'Cookie': `JSESSIONID=${jsessionId}`},
        //     });
        // }

        return next.handle(req.clone({
            withCredentials: true
        }));
    }
}