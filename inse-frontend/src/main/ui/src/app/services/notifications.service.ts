import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class NotificationsService {
  constructor(private zone: NgZone, private http: HttpClient) {}

  stream(): Observable<any> {
    return this.http.get(environment.NOTIFICATIONS_STREAM_URL, {}).pipe(
      tap(
        (data) => console.log(data),
        (error) => console.error(error)
      )
    );
  }
}
