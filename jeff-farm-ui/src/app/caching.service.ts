import { Observable, of } from 'rxjs';
import { share, tap } from 'rxjs/operators';

import { HttpEvent, HttpHandler, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class CachingService {

    completedCache = new Map<string, HttpResponse<any>>();
    pendingCache = new Map<string, Observable<HttpEvent<any>>>();

    handle(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.pendingCache.has(req.url)) {
            return this.pendingCache.get(req.url);
        }
        if (this.completedCache.has(req.url)) {
            return of(this.completedCache.get(req.url));
        }
        const pendingRequest: Observable<HttpEvent<any>> = next.handle(req)
            .pipe(
                share(),
                tap(value => {
                    if (value instanceof HttpResponse) {
                        this.completedCache.set(req.url, value);
                        this.pendingCache.delete(req.url);
                    }
                }));
        this.pendingCache.set(req.url, pendingRequest);
        return pendingRequest;
    }

    clear() {
        this.completedCache.clear();
        this.pendingCache.clear();
    }

    remove(url: string) {
        this.completedCache.delete(url);
        this.pendingCache.delete(url);
    }
}
