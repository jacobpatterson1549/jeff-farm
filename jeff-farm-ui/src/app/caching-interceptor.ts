import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CachingService } from './caching.service';

@Injectable({
    providedIn: 'root'
})
export class CachingInterceptor implements HttpInterceptor {

    constructor(private cachingService: CachingService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        switch (req.method) {
            case 'GET':
                return this.cachingService.handle(req, next);
            case 'POST':
            case 'PUT':
            case 'DELETE':
                this.cachingService.remove(req.url);
                const parentUrl = req.url.substr(0, req.url.lastIndexOf('/'));
                this.cachingService.remove(parentUrl);
                const grandParentUrl = parentUrl.substr(0, parentUrl.lastIndexOf('/'));
                this.cachingService.remove(grandParentUrl);

                return next.handle(req);

            default:
                return next.handle(req);
        }
    }
}
