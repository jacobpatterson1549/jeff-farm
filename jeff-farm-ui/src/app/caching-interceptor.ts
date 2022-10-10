import { Observable } from 'rxjs';

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CachingService } from './caching.service';

@Injectable({
    providedIn: 'root'
})
export class CachingInterceptor implements HttpInterceptor {

    constructor(
        private cachingService: CachingService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        switch (req.method) {
            case 'GET':
                return this.cachingService.handle(req, next);
            case 'POST':
            case 'PUT':
                this.clearCache(req.url);
                break;
            case 'DELETE':
                this.clearCache(req.url.substring(0, req.url.lastIndexOf('/')));
                break;
            default:
                break;
        }
        return next.handle(req);
    }

    private clearCache(url: string) {
        this.cachingService.remove(url);
    }
}
