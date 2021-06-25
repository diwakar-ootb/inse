import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PhotosService} from '../../services/photos.service';
import {MoviesService} from '../../services/movies.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  list = [];
  progress: number;

  constructor(private router: Router, private moviesService: MoviesService) {
  }

  ngOnInit(): void {
    this.loadData();
    this.showProgress();
  }

  loadData(): void {
    this.moviesService.stream().subscribe(data => {
      console.log(data);
      this.list.push(data);
    });
  }

  showProgress(): void {
    this.moviesService.progress().subscribe(data => {
      this.progress = data.data * 5;
    });
  }
}
