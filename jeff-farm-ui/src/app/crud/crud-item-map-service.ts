import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { HttpClient } from '@angular/common/http';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { CrudItemCoordinate } from './crud-item-coordinate';
import { CrudItemMap } from './crud-item-map';
import { CrudItemMapUpdate } from './crud-item-map-update';
import { CrudItemService } from './crud-item.service';

export abstract class CrudItemMapService extends CrudItemService<CrudItemMap> {

    constructor(
        typeName: string,
        errorMessagesService: ErrorMessagesService,
        http: HttpClient) {
        super(
            typeName,
            errorMessagesService,
            http);
    }

    createCrudItem(): CrudItemMap {
        return new CrudItemMap(+this.getParentId());
    }

    createCrudItemCoordinate(): CrudItemCoordinate {
        return new CrudItemCoordinate(+this.getId());
    }

    get(): Observable<CrudItemMap> {
        return super.get()
            .pipe(
                tap((crudItemMap: CrudItemMap) =>
                    crudItemMap.coordinates.forEach((data, index, coordinates) =>
                        coordinates[index] = Object.assign(
                            this.createCrudItemCoordinate(),
                            data))));
    }

    put(crudItemMap: CrudItemMap, updatedValue: any): Observable<object> {
        const addCoordinates: CrudItemCoordinate[] = [];
        for (let i = updatedValue.coordinates.length - 1; i >= 0; i--) {
            const coordinate: CrudItemCoordinate = updatedValue.coordinates[i];
            if (!coordinate.id) {
                addCoordinates.push(coordinate);
                updatedValue.coordinates.splice(i, 1);
            }
        }
        const removeItemIds: number[] = [];
        for (let i = crudItemMap.coordinates.length - 1; i >= 0; i--) {
            const coordinate: CrudItemCoordinate = crudItemMap.coordinates[i];
            if (!updatedValue.coordinates.find(updatedCoordinate =>
                updatedCoordinate.id === coordinate.id)) {
                removeItemIds.push(coordinate.id);
                crudItemMap.coordinates.splice(i, 1);
            }
        }
        Object.assign(crudItemMap, updatedValue);
        const crudItemMapUpdate: CrudItemMapUpdate
            = new CrudItemMapUpdate(crudItemMap, addCoordinates, removeItemIds);
        const url = this.getBaseUrl();
        return this.http.put(url, crudItemMapUpdate)
            .pipe(
                catchError(this.errorMessagesService.handleError<any>('update')),
            );
    }

    getTargets(): Observable<Map<number, string>> {
        const url = `${this.getBaseUrl()}/targets/${this.getParentId()}`;
        return this.http.get<Map<number, string>>(url)
            .pipe(
                catchError(this.errorMessagesService.handleError<any>('targets')),
            );
    }
}
