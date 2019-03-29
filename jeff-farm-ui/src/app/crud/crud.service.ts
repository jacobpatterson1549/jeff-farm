import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { CrudItem } from './crud.item';
import { ActivatedRoute } from '@angular/router';

export interface CrudChild {
  pluralName: string;
  path: string;
}

export abstract class CrudService<T extends CrudItem> {

  private route: ActivatedRoute;

  constructor(
    private http: HttpClient) { }

  abstract createCrudItem(): T;

  abstract getPluralName(): string;

  abstract getCrudChildren(): CrudChild[];

  abstract getBaseUrl(): string;

  setRoute(route: ActivatedRoute) {
    this.route = route;
  }

  getSingularName() {
    const pluralName: string = this.getPluralName();
    return pluralName.substring(0, pluralName.length - 1);
  }

  genBaseUrl(): string {
    return `http://localhost:8080/jeff-farm-ws/${this.getBaseUrl()}`;
  }

  post(t: T): Observable<Number> {
    return this.http.post<Number>(this.genBaseUrl(), t)
      .pipe(
        catchError(this.handleError),
        );
  }

  get(): Observable<T> {
    const url = `${this.genBaseUrl()}/${this.getId()}`;
    return this.http.get<T>(url)
      .pipe(
        map((data: T) => Object.assign(this.createCrudItem(), data)),
        catchError(this.handleError),
        );
  }

  getList(): Observable<T[]> {
    return this.http.get<T[]>(this.genBaseUrl())
      .pipe(
        map((dataList: T[]) => dataList
          .map(data => Object.assign(this.createCrudItem(), data))),
        catchError(this.handleError),
        );
  }

  put(t: T): Observable<Object> {
    return this.http.put(this.genBaseUrl(), t)
      .pipe(
        catchError(this.handleError),
        );
  }

  delete(): Observable<Object> {
    const url = `${this.genBaseUrl()}/${this.getId()}`;
    return this.http.delete(url)
      .pipe(
        catchError(this.handleError),
        );
  }

  canDelete(): Observable<boolean> {
    const url = `${this.genBaseUrl()}/${this.getId()}/canDelete`;
    return this.http.get<boolean>(url)
      .pipe(
        catchError(this.handleError),
      );
  }

  private handleError(error: HttpErrorResponse) {
    alert(error.message);
    return throwError('error');
  }

  protected getRouteParam(paramName: string): string {
    const param: string = this.route.snapshot.paramMap.get(paramName);
    return param;
  }

  getId(): string {
    return this.getRouteParam('id') || '';
  }
}
