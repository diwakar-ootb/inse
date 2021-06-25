import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class NetworkService {
  constructor(private zone: NgZone, private http: HttpClient) {}

  stream(): Observable<any> {
    return this.http.get(environment.NETWORK_STREAM_URL, {}).pipe(
      tap(
        (data) => console.log(data),
        (error) => console.error(error)
      )
    );
  }

  explore(): Observable<any> {
    return this.http.get(environment.NETWORK_EXPLORE_URL, {}).pipe(
      tap(
        (data) => console.log(data),
        (error) => console.error(error)
      )
    );
  }
}
