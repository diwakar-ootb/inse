import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';
import { RippleModule } from 'primeng/ripple';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { FormsModule } from '@angular/forms';

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
import { NotesComponent } from 'src/app/module/notes/notes.component';

import { FeedsService } from '../../../services/feeds.service';
import { NotificationsService } from '../../../services/notifications.service';
import { MessagesService } from '../../../services/messages.service';
import { NetworkService } from 'src/app/services/network.service';

import { MessageService } from 'primeng/api';

@NgModule({
  declarations: [
    DefaultComponent,
    DashboardComponent,
    NetworkComponent,
    MessageComponent,
    NotificationComponent,
    AccountComponent,
    NotesComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RippleModule,
    ButtonModule,
    ToastModule,
    FormsModule,
    SharedModule,
    RouterModule,
    FlexLayoutModule,
    DefaultRoutingModule,
  ],
  exports: [],
  providers: [
    MessageService,
    NotificationsService,
    MessagesService,
    NetworkService,
    FeedsService,
    { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true },
  ],
})
export class DefaultModule {}
