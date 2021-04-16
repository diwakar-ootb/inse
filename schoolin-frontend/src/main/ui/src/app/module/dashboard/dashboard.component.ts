import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PhotosService } from '../../services/photos.service';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
	data: any;
	list = [];

	constructor(private router: Router, private photosService: PhotosService) {
	}

	ngOnInit(): void {
		this.loadData();
	}

	loadData(): void {
		this.photosService.stream().subscribe(data => {
			console.log(data);
			this.list.push(JSON.parse(data.data));
		});
	}

	getBackdrop(item: any): string {
		return 'https://image.tmdb.org/t/p/original/' + item.poster_path;
	}
}
