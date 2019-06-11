import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, map, catchError } from 'rxjs/operators';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { CrudItemGroup } from './crud.item.group';
import { CrudItem } from './crud.item';
import { CrudItemService } from './crud.item.service';
import { CrudItemInspection } from './crud.item.inspection';

// TODO: Rename CrudService to CrudItemsService
export abstract class CrudItemGroupsService<U extends CrudItem, V extends CrudItemInspection<U>, T extends CrudItemGroup<V>>
  extends CrudItemService<T> {

  constructor(
    errorMessagesService: ErrorMessagesService,
    http: HttpClient) {
    super(errorMessagesService, http);
  }

  abstract createCrudItemInspection(): V;

  get(): Observable<T> {
    return super.get()
      .pipe(
        tap((crudItemGroup: T) =>
          crudItemGroup.inspectionItems.forEach((data, index, items) =>
            items[index] = Object.assign(this.createCrudItemInspection(), data))));
  }

  // TODO: specify which old items to delete, add
  // put(t: T): Observable<object> {...}

  getTargets(): Observable<Map<number, string>> {
    const url = `${this.getBaseUrl()}/targets`;
    return this.http.get<Map<number, string>>(url)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('targets')),
      );
  }
}
