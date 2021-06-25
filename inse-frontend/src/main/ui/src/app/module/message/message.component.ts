import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { MessagesService } from 'src/app/services/messages.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css'],
})
export class MessageComponent implements OnInit {
  list: any;
  message: any;

  constructor(
    private router: Router,
    private messagesService: MessagesService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.stream();
  }

  stream(): void {
    this.messagesService.stream().subscribe((data) => {
      console.log(data);
      this.list = data;
    });
  }

  loadMessage(item): void {
    this.messagesService.load(item.id).subscribe((data) => {
      console.log(data);
      this.message = data;
    });
  }
}
