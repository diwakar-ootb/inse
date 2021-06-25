import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { tap } from 'rxjs/operators';
import { NativeEventSource, EventSourcePolyfill } from 'event-source-polyfill';
import { FeedModel } from '../core/model/feed.model';

@Injectable({
	providedIn: 'root'
})
export class PhotosService {

	constructor(private zone: NgZone, private http: HttpClient) { }

	load(): Observable<any> {
		return this.http.get(environment.POSTS_URL, {}).pipe(
			tap(
				data => console.log(data),
				error => console.error(error)
			)
		);
	}

	list(): Observable<any> {
		return this.http.get(environment.POSTS_URL, {}).pipe(
			tap(
				data => console.log(data),
				error => console.error(error)
			)
		);
	}

  stream1(): Observable<any> {
		const jwt = sessionStorage.getItem("jwt");
		const authParam = '?Authorization=' + jwt;
    return this.http.get(environment.POSTS_STREAM_URL + authParam, {}).pipe(
			tap(
				data => console.log(data),
				error => console.error(error)
			)
		);
	}

	stream(): Observable<any> {
		const jwt = sessionStorage.getItem("jwt");
		//const EventSource = EventSourcePolyfill;
		const authParam = '?Authorization=' + jwt;
		return new Observable<any>((observer) => {
			const eventSource = new EventSource(environment.POSTS_STREAM_URL + authParam);
			eventSource.onmessage = (event: MessageEvent<any>) => {
				this.zone.run(() => {
					observer.next(event);
				});
			};

			eventSource.onerror = (event: MessageEvent<any>) => {
				this.zone.run(() => {
					observer.error(event);
				});
			};

			return () => {
				eventSource.close();
			};
		});
	}

  save(post: any) {
    const jwt = sessionStorage.getItem("jwt");
		const authParam = '?Authorization=' + jwt;
    return this.http.post<any>(environment.POSTS_SAVE_URL + authParam, post).pipe(
			tap(
				data => console.log(data),
				error => console.error(error)
			)
		);
	}
}
