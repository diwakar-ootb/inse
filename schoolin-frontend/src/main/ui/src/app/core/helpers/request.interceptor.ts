import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {
	constructor() {
	}

	intercept(
		request: HttpRequest<any>,
		next: HttpHandler
	): Observable<HttpEvent<any>> {
		let params = {};
		const jwt = sessionStorage.getItem("jwt");
		request = request.clone({
			withCredentials: true,
			setParams: params,
			headers: new HttpHeaders({ 'Authorization': '' + jwt }),
		});

		return next.handle(request).pipe(
			tap(
				(event: HttpEvent<any>) => {
				},

				(err: any) => {
					console.log(err);
				}
			)
		);
	}
}
