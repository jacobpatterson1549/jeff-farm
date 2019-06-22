import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { HttpClient } from '@angular/common/http';

import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { CrudItem } from './crud-item';
import { CrudItemInspection } from './crud-item-inspection';
import { CrudItemInspectionGroup } from './crud-item-inspection-group';
import { CrudItemInspectionGroupUpdate } from './crud-item-inspection-group-update';
import { CrudItemService } from './crud-item.service';

export abstract class CrudItemInspectionGroupService<
  U extends CrudItem, V extends CrudItemInspection<U>,
  T extends CrudItemInspectionGroup<V>>
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

  put(t: T, updatedValue: any): Observable<object> {
    const addItems: V[] = [];
    for (let i = updatedValue.inspectionItems.length - 1; i >= 0; i--) {
      const inspectionItem: V = updatedValue.inspectionItems[i]; // not really a V, but like one
      if (!inspectionItem.id) {
        addItems.push(inspectionItem);
        updatedValue.inspectionItems.splice(i, 1);
      }
    }
    const removeItemIds: number[] = [];
    for (let i = t.inspectionItems.length - 1; i >= 0; i--) {
      const inspectionItem: V = t.inspectionItems[i];
      if (!updatedValue.inspectionItems.find(updatedInspectionItem => updatedInspectionItem.id === inspectionItem.id)) {
        removeItemIds.push(inspectionItem.id);
        t.inspectionItems.splice(i, 1);
      }
    }
    Object.assign(t, updatedValue);
    const inspectionGroupUpdate: CrudItemInspectionGroupUpdate<V, T> = new CrudItemInspectionGroupUpdate(t, addItems, removeItemIds);
    const url = this.getBaseUrl();
    return this.http.put(url, inspectionGroupUpdate)
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
