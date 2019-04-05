import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class HostInterceptor implements HttpInterceptor {

    private readonly HOST_URL: string = '/api/';

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // const hostReq = req.clone({ url: this.HOST_URL + req.url });
        // return next.handle(hostReq);
        return next.handle(req);
    }
}