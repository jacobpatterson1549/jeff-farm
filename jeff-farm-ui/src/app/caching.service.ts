import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class CachingService {

    cache = new Map<string, HttpResponse<any>>();

    isCached(url: string): boolean {
        return this.cache.has(url);
    }

    get(url: string): HttpResponse<any> {
        return this.cache.get(url);
    }

    add(url: string, httpResponse: HttpResponse<any>) {
        if (!this.isCached(url)) {
            this.cache.set(url, httpResponse);
        }
    }

    clear() {
        this.cache.clear();
    }

    remove(url: string) {
        this.cache.delete(url);
    }
}