import { Observable } from 'rxjs';

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CachingService } from './caching.service';
import { CrudItem } from './crud/crud-item';
import { CrudItemInspectionGroupUpdate } from './crud/crud-item-inspection-group-update';

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
                this.clearCache(`${req.url}/${this.getId(req.body)}`, this.getParentId(req.body));
                break;
            case 'DELETE':
                this.clearCache(req.url, this.getParentId(req.body));
                break;
            default:
                break;
        }
        return next.handle(req);
    }

    private clearCache(idUrl: string, parentId: number) {
        this.cachingService.remove(idUrl);
        const canDeleteUrl = `${idUrl}/canDelete`;
        this.cachingService.remove(canDeleteUrl);
        const parentUrl = idUrl.substr(0, idUrl.lastIndexOf('/'));
        this.cachingService.remove(parentUrl);
        const listUrl = `${parentUrl}/list/${parentId}`;
        this.cachingService.remove(listUrl);
        const targetsUrl = `${parentUrl}/inspectionGroup/targets/${parentId}`;
        this.cachingService.remove(targetsUrl);
    }

    private getId(body: any): number {
        if (body instanceof CrudItem) {
            return body.id;
        }
        if (body instanceof CrudItemInspectionGroupUpdate) {
            return body.group.id;
        }
        return null;
    }

    private getParentId(body: any): number {
        if (body instanceof CrudItem) {
            return body.parentId;
        }
        return null;
    }
}
