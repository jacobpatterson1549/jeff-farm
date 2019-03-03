import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import { Observable, of, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { CrudItem } from '../classes/crud.item';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

export abstract class CrudService<T extends CrudItem> {

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  abstract getBaseUrl(): string;

  genBaseUrl(): string {
    return `http://localhost:8080/jeff-farm-ws/${this.getBaseUrl()}`;
  }

  create(t: T): Observable<any> {
    return this.http.post<Object>(this.genBaseUrl(), t, httpOptions)
      .pipe(catchError(this.handleError));
  }

  get(): Observable<T> {
    const url = `${this.genBaseUrl()}/${this.getId()}`;
    return this.http.get<T>(url, httpOptions)
      .pipe(catchError(this.handleError));
  }

  getList(): Observable<T[]> {
    return this.http.get<T[]>(this.genBaseUrl(), httpOptions)
      .pipe(catchError(this.handleError));
  }

  update(t: T): Observable<Object> {
    return this.http.put(this.genBaseUrl(), t, httpOptions)
      .pipe(catchError(this.handleError));
  }

  delete(): Observable<Object> {
    const url = `${this.genBaseUrl()}/${this.getId()}`;
    return this.http.delete(url, httpOptions)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    console.error(error);
    alert(error.message);
    return throwError('error');
  }

  protected getRouteParam(name: string) : number {
    const id: string = this.route.snapshot.paramMap.get(name);
    return parseInt(id);
  }

  getId(): number {
    return this.getRouteParam('id');
  }
}