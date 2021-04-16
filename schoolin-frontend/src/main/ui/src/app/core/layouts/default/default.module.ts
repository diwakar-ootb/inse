import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule  } from '@angular/common';
import {RippleModule} from 'primeng/ripple';
import {ButtonModule} from 'primeng/button';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { RequestInterceptor } from '../../helpers/request.interceptor';
import { SharedModule } from '../../shared/shared.module';


import { DefaultRoutingModule } from './default-routing.module';

import { DefaultComponent } from './default.component';
import { DashboardComponent } from '../../../module/dashboard/dashboard.component';
import { NetworkComponent } from './../../../module/network/network.component';
import { MessageComponent } from 'src/app/module/message/message.component';
import { NotificationComponent } from 'src/app/module/notification/notification.component';
import { AccountComponent } from 'src/app/module/account/account.component';

import { PhotosService } from '../../../services/photos.service';

@NgModule({
	declarations: [
		DefaultComponent,
		DashboardComponent,
    NetworkComponent,
    MessageComponent,
    NotificationComponent,
    AccountComponent
	],
	imports: [
		CommonModule,
		HttpClientModule,
    RippleModule,
    ButtonModule,
		SharedModule,
		RouterModule,
		FlexLayoutModule,
		DefaultRoutingModule
	],
	exports: [
	],
	providers: [
		PhotosService,
		{ provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true },
	],
})
export class DefaultModule { }
