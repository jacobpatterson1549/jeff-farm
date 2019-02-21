import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Farm } from './classes/farm';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FarmService {

  private farmsUrl = 'http://localhost:8080/jeff-farm-ws/farm';

  constructor(private http: HttpClient) { }

  createFarm(farm: Farm): Observable<any> {
    return this.http.post(this.farmsUrl, farm, httpOptions)
      .pipe(catchError(this.handleError('createFarm')));
  }

  getFarms(): Observable<Farm[]> {
    return this.http.get<Farm[]>(this.farmsUrl, httpOptions)
      .pipe(catchError(this.handleError('getFarms', [])));
  }

  updateFarm(farm: Farm): Observable<any> {
    return this.http.put(this.farmsUrl, farm, httpOptions)
      .pipe(catchError(this.handleError('updateFarm')));
  }

  deleteFarm(farm: Farm | number): Observable<any> {
    const id = typeof farm === 'number' ? farm : farm.id;
    const url = this.farmsUrl + '/' + id;
    return this.http.delete(url, httpOptions)
      .pipe(catchError(this.handleError('deleteFarm')));
  }

  // copied from heroes tutorial
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
