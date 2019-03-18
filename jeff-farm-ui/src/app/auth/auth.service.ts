import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { User } from '../user/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  // TODO: combine common url with crud service
  loginUrl: string = 'http://localhost:8080/jeff-farm-ws/login';
  isLoggedIn: boolean = false;

  constructor(private httpClient: HttpClient) {
    // TODO: check to see if user is logged in [and set loginUrl]
  }

  login(username: string, password: string): Observable<any> {

    const user: User = new User();
    user.userName = username;
    user.password = password;
    return this.httpClient.post<any>(this.loginUrl, user, httpOptions)
      .pipe(
        tap(result => {
                this.isLoggedIn = true;
              }), // TODO: ensure logging in with an invalid password does not trigger this
              // TODO: store session id in local storage
        );
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     "Authorization": "Basic " + btoa(`${username}:${password}`),
    //     "Content-Type": "application/x-www-form-urlencoded",
    //   }),
    // };

  }

  logout(): void {
    this.httpClient.get<any>('http://localhost:8080/jeff-farm-ws/user/logout')
      .pipe(
        tap(result => {
          this.isLoggedIn = false;
        })
      );
    // TODO: invalidate session [and saved session id]
  }
}
