import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
	selector: 'app-sidebar',
	templateUrl: './sidebar.component.html',
	styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent implements OnInit {
	sidebarClass = 'sidebar-menu layout-component';

	constructor(
		private router: Router
	) {
	}

	ngOnInit() {
	}
}
