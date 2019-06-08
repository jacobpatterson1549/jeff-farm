import { Injectable } from '@angular/core';
import { HttpEvent, HttpRequest, HttpHandler, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, share } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class CachingService {

    completedCache = new Map<string, HttpResponse<any>>();
    pendingCache = new Map<string, Observable<HttpEvent<any>>>();

    handle(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const completedResponse = this.completedCache.get(req.url) || null;
        if (completedResponse) {
            return of(completedResponse);
        }

        const pendingResponse = this.pendingCache.get(req.url) || null;
        if (pendingResponse) {
            return pendingResponse;
        }

        const pendingRequest = next.handle(req)
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
