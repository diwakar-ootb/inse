import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class MessagesService {
  constructor(private zone: NgZone, private http: HttpClient) {}

  stream(): Observable<any> {
    return this.http.get(environment.MESSAGES_STREAM_URL, {}).pipe(
      tap(
        (data) => console.log(data),
        (error) => console.error(error)
      )
    );
  }

  load(id: any) {
    return this.http.get(environment.MESSAGE_GET_URL + id, {}).pipe(
      tap(
        (data) => console.log(data),
        (error) => console.error(error)
      )
    );
  }
}
