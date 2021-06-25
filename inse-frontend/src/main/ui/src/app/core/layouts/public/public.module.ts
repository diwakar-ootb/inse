import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PublicComponent } from './public.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { PasswordModule } from 'primeng/password';

import { PublicRoutingModule } from './public-routing.module';

import { LoginComponent } from '../../../module/login/login.component';

import { AuthService } from '../../../services/auth.service';

@NgModule({
	declarations: [PublicComponent, LoginComponent],
	imports: [
		CommonModule,
		HttpClientModule,
		RouterModule,
		DropdownModule,
		ButtonModule,
		InputTextModule,
		PasswordModule,
		CardModule,
		PublicRoutingModule
	],
	exports: [
	],
	providers: [
		AuthService
	],
})
export class PublicModule { }
