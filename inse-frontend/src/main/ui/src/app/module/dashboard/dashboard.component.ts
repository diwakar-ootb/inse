import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FeedsService } from '../../services/feeds.service';

import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  data: any;
  list = [];
  message: any;

  constructor(
    private router: Router,
    private feedsService: FeedsService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.stream();
  }

  stream(): void {
    this.feedsService.stream1().subscribe((data) => {
      console.log(data);
      // this.list.push(JSON.parse(data.data));
      this.list = data;
    });
  }

  post(): void {
    const title = 'Post ' + Math.random() * 10;
    const data = {
      title: title,
      description: this.message,
    };
    this.feedsService.save(data).subscribe((result) => {
      this.messageService.add({
        severity: 'info',
        summary: 'Posted',
        detail: 'Message Posted',
        sticky: true,
      });
      this.stream();
      this.message = '';
    });
  }

  getBackdrop(item: any): string {
    return 'https://image.tmdb.org/t/p/original/' + item.poster_path;
  }
}
