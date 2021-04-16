import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  auth(user: any): Observable<any> {
    return this.http.post(environment.AUTH_URL, user, {observe: 'response'}).pipe(
      tap(
        data => console.log(user.username + ' : ' + user.password, data),
        error => console.error(user.username + ' : ' + user.password, error)
      )
    );
  }
}
