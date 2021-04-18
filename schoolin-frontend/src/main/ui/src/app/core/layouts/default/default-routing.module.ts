import { AccountComponent } from './../../../module/account/account.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { DefaultComponent } from './default.component';
import { DashboardComponent } from '../../../module/dashboard/dashboard.component';
import { NetworkComponent } from './../../../module/network/network.component';
import { MessageComponent } from 'src/app/module/message/message.component';
import { NotificationComponent } from 'src/app/module/notification/notification.component';
import { NotesComponent } from 'src/app/module/notes/notes.component';

const routes: Routes = [
	{
		path: '',
		component: DefaultComponent,
		children: [
			{ path: 'dashboard', component: DashboardComponent },
      { path: 'network', component: NetworkComponent },
      { path: 'message', component: MessageComponent },
      { path: 'notification', component: NotificationComponent },
      { path: 'account', component: AccountComponent },
      { path: 'notes', component: NotesComponent },
      { path: 'dashboard', component: DashboardComponent },
			{
				path: '',
				redirectTo: '',
				pathMatch: 'full'
			}
		]
	},

];

@NgModule({
	declarations: [],
	imports: [
		CommonModule,
		RouterModule.forChild(routes),
	],
	exports: [RouterModule]
})
export class DefaultRoutingModule { }
