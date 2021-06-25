import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { NotificationsService } from 'src/app/services/notifications.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css'],
})
export class NotificationComponent implements OnInit {
  list: any;

  constructor(
    private router: Router,
    private notificationsService: NotificationsService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.stream();
  }

  stream(): void {
    this.notificationsService.stream().subscribe((data) => {
      console.log(data);
      this.list = data;
    });
  }
}
