import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { CrudItem } from './crud.item';
import { ActivatedRoute } from '@angular/router';
import { ErrorMessagesService } from '../error-messages/error-messages.service';

export interface CrudChild {
  pluralName: string;
  path: string;
}

export abstract class CrudService<T extends CrudItem> {

  private route: ActivatedRoute;

  constructor(
    private errorMessagesService: ErrorMessagesService,
    private http: HttpClient) { }

  abstract createCrudItem(): T;

  abstract getPluralName(): string;

  // The groups visible at the list level for this CrudItem
  getCrudGroups(): CrudChild[] {
    return [];
  }

  // The children that are descendents of individual items
  getCrudChildren(): CrudChild[] {
    return [];
  }

  abstract getBaseUrl(): string;

  setRoute(route: ActivatedRoute) {
    this.route = route;
  }

  getSingularName(): string {
    const pluralName: string = this.getPluralName();
    return pluralName.substring(0, pluralName.length - 1);
  }

  post(t: T): Observable<number> {
    return this.http.post<number>(this.getBaseUrl(), t)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('create')),
      );
  }

  get(): Observable<T> {
    const url = this.getIdUrl();
    return this.http.get<T>(url)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('read')),
        map((data: T) => Object.assign(this.createCrudItem(), data)),
      );
  }

  getList(): Observable<T[]> {
    return this.http.get<T[]>(this.getBaseUrl())
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('read-list')),
        map((dataList: T[]) => dataList
          .map(data => Object.assign(this.createCrudItem(), data))),
      );
  }

  put(t: T): Observable<object> {
    const url = this.getIdUrl();
    return this.http.put(url, t)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('update')),
      );
  }

  delete(): Observable<object> {
    const url = this.getIdUrl();
    return this.http.delete(url)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('delete')),
      );
  }

  canDelete(): Observable<boolean> {
    const url = `${this.getIdUrl()}/canDelete`;
    return this.http.get<boolean>(url)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('can-delete')),
      );
  }

  protected getRouteParam(paramName: string): string {
    const param: string = this.route == null ? null : this.route.snapshot.paramMap.get(paramName);
    return param;
  }

  getId(): string {
    return this.getRouteParam('id') || '';
  }

  private getIdUrl(): string {
    return `${this.getBaseUrl()}/${this.getId()}`;
  }
}
