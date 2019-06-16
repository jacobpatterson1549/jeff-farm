import { HttpClient, HttpParams } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { CrudItem } from './crud-item';
import { ErrorMessagesService } from '../error-messages/error-messages.service';
import { CrudChild } from './crud-child';

export abstract class CrudItemService<T extends CrudItem> {

  private route: ActivatedRoute;

  constructor(
    protected errorMessagesService: ErrorMessagesService,
    protected http: HttpClient) { }

  abstract createCrudItem(): T;

  // The groups visible at the list level for this CrudItem
  getCrudGroups(): CrudChild[] {
    return [];
  }

  // The children that are descendents of individual items
  getCrudChildren(): CrudChild[] {
    return [];
  }

  protected abstract getBaseUrl(): string;

  abstract getTypeName(): string;

  setRoute(route: ActivatedRoute) {
    this.route = route;
  }

  post(t: T, createdValue: any): Observable<number> {
    Object.assign(t, createdValue);
    return this.http.post<number>(this.getBaseUrl(), t)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('create')),
      );
  }

  get(): Observable<T> {
    localStorage.setItem(`${this.getTypeName()}Id`, this.getId());
    const url = this.getIdUrl();
    return this.http.get<T>(url)
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('read')),
        map((data: T) => Object.assign(this.createCrudItem(), data)),
      );
  }

  getList(): Observable<T[]> {
    const url = `${this.getBaseUrl()}/list`;
    const listHttpParams = this.getListHttpParams();
    return this.http.get<T[]>(url, {params: listHttpParams})
      .pipe(
        catchError(this.errorMessagesService.handleError<any>('list')),
        map((dataList: T[]) => dataList
          .map(data => Object.assign(this.createCrudItem(), data))),
      );
  }

  put(t: T, updatedValue: any): Observable<object> {
    Object.assign(t, updatedValue);
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

  protected getIdUrl(): string {
    const id = this.getId();
    return this.getBaseUrl().concat(id.length > 0 ? '/' : '').concat(id);
  }

    // used to specify the parentId in a query param when calling the [GET]/list endpoint.
    protected getListHttpParams(): HttpParams {
      return new HttpParams();
    }
}
