import { HttpClient } from '@angular/common/http';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { CrudItemGroup } from './crud.item.group';
import { CrudItem } from './crud.item';
import { CrudService } from './crud.service';
import { Observable } from 'rxjs';
import { tap, map } from 'rxjs/operators';

// TODO: Rename CrudService to CrudItemsService
export abstract class CrudItemGroupsService<U extends CrudItem, V extends CrudItem, T extends CrudItemGroup<V>> extends CrudService<T> {

    constructor(
        private selectItemService: CrudService<U>,
        private errorMessagesService1: ErrorMessagesService,
        private http1: HttpClient) {
        super(errorMessagesService1, http1);
    }

    // TODO: specify which old items to delete, add
    // put(t: T): Observable<object> {...}

    getSelectItems(): Observable<Map<number, U>> {
        return this.selectItemService.getList()
            .pipe(map((items: U[]) => items.reduce((m, item) => {
                m[item.id] = item;
                return m;
            }, new Map())));
    }
}
