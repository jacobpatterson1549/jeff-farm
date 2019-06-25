import { Observable } from 'rxjs';

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from '../environments/environment';

@Injectable()
export class HostInterceptor implements HttpInterceptor {

    private readonly HOST_URL: string = environment.SERVER_URL;

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req.clone({ url: this.HOST_URL + req.url }));
    }
}
