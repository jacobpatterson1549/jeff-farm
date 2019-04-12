import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

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

  post(t: T): Observable<Number> {
    return this.http.post<Number>(this.getBaseUrl(), t);
  }

  get(): Observable<T> {
    const url = `${this.getBaseUrl()}/${this.getId()}`;
    return this.http.get<T>(url)
      .pipe(
        map((data: T) => Object.assign(this.createCrudItem(), data)),
        );
  }

  getList(): Observable<T[]> {
    return this.http.get<T[]>(this.getBaseUrl())
      .pipe(
        map((dataList: T[]) => dataList
          .map(data => Object.assign(this.createCrudItem(), data))),
        );
  }

  put(t: T): Observable<Object> {
    return this.http.put(this.getBaseUrl(), t);
  }

  delete(): Observable<Object> {
    const url = `${this.getBaseUrl()}/${this.getId()}`;
    return this.http.delete(url);
  }

  canDelete(): Observable<boolean> {
    const url = `${this.getBaseUrl()}/${this.getId()}/canDelete`;
    return this.http.get<boolean>(url);
  }

  protected getRouteParam(paramName: string): string {
    const param: string = this.route == null ? null : this.route.snapshot.paramMap.get(paramName);
    return param;
  }

  getId(): string {
    return this.getRouteParam('id') || '';
  }
}
