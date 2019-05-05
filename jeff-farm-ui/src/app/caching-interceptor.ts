import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { CachingService } from './caching.service';

@Injectable()
export class CachingInterceptor implements HttpInterceptor {

    constructor(private cachingService: CachingService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        switch (req.method) {

            case 'GET':
                const isCached: boolean = this.cachingService.isCached(req.url);
                return isCached
                    ? of(this.cachingService.get(req.url))
                    : next.handle(req).pipe(tap(value => {
                        if (value instanceof HttpResponse) {
                            this.cachingService.add(req.url, value);
                        }
                    }));

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
