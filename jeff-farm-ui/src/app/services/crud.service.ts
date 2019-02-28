import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { CrudItem } from '../classes/crud.item';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

export abstract class CrudService<T extends CrudItem> {

  constructor(private http: HttpClient, private baseUrl:string) {
    this.baseUrl = `http://localhost:8080/jeff-farm-ws/${baseUrl}`;
  }

  create(t: T): Observable<any> {
    return this.http.post(this.baseUrl, t, httpOptions)
      .pipe(catchError(this.handleError('create')));
  }

  get(id: number): Observable<T> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<T>(url, httpOptions)
      .pipe(catchError(this.handleError(`get/${id}`, null)));
  }

  getList(): Observable<T[]> {
    return this.http.get<T[]>(this.baseUrl, httpOptions)
      .pipe(catchError(this.handleError('getList', [])));
  }

  update(t: T): Observable<any> {
    return this.http.put(this.baseUrl, t, httpOptions)
      .pipe(catchError(this.handleError('update')));
  }

  delete(t: T | number): Observable<any> {
    const id = typeof t === 'number' ? t : t.id;
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete(url, httpOptions)
      .pipe(catchError(this.handleError(`delete/${id}`)));
  }

  // copied from heroes tutorial
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}