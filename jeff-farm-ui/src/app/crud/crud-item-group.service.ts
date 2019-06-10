import { HttpClient } from '@angular/common/http';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { CrudItemGroup } from './crud.item.group';
import { CrudItem } from './crud.item';
import { CrudService } from './crud.service';
import { Observable } from 'rxjs';
import { tap, map, catchError } from 'rxjs/operators';
import { CrudItemInspection } from './crud.item.inspection';

// TODO: Rename CrudService to CrudItemsService
export abstract class CrudItemGroupsService<V extends CrudItemInspection, T extends CrudItemGroup<V>>
    extends CrudService<T> {

    constructor(
        errorMessagesService: ErrorMessagesService,
        http: HttpClient) {
        super(errorMessagesService, http);
    }

    // TODO: specify which old items to delete, add
    // put(t: T): Observable<object> {...}

    getTargets(): Observable<Map<number, string>> {
        return this.http.get<Map<number, string>>(this.getBaseUrl())
          .pipe(
            catchError(this.errorMessagesService.handleError<any>('targets')),
          );
      }
}
