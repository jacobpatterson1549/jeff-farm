import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JsonInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // const jsonReq = req.clone({ setHeaders: { 'Content-Type': 'application/json' } });
        const jsonReq = req.clone({ headers: req.headers.set('Content-Type', 'application/json') });
        // const jsonReq = req;

        return next.handle(jsonReq);
    }
}