import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ToolbarModule } from 'primeng/toolbar';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { SidebarModule } from 'primeng/sidebar';
import { PanelMenuModule } from 'primeng/panelmenu';
import { ScrollPanelModule } from 'primeng/scrollpanel';

import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
	declarations: [HeaderComponent, SidebarComponent, FooterComponent],
	imports: [
		CommonModule,
		RouterModule,
		ToolbarModule,
		OverlayPanelModule,
		SidebarModule,
		ScrollPanelModule,
		PanelMenuModule,
	],
	exports: [HeaderComponent, SidebarComponent, FooterComponent],
})
export class SharedModule { }
