import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

// Local Components
import { LandingComponent } from './module/landing/landing.component';

const routes: Routes = [
	{
		path: 'p',
		loadChildren: () => import('./core/layouts/public/public.module').then(m => m.PublicModule)
	},
	{
		path: 's',
		loadChildren: () => import('./core/layouts/default/default.module').then(m => m.DefaultModule)
	},
	{
		path: 'l',
		component: LandingComponent
	},
	{
		path: '',
		redirectTo: '/l',
		pathMatch: 'full'
	}
];

@NgModule({
	declarations: [],
	imports: [
		RouterModule.forRoot(routes),
		CommonModule
	],
	exports: [RouterModule]
})
export class AppRoutingModule { }
