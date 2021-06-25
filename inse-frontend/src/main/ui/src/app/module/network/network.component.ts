import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { NetworkService } from 'src/app/services/network.service';

@Component({
  selector: 'app-network',
  templateUrl: './network.component.html',
  styleUrls: ['./network.component.css'],
})
export class NetworkComponent implements OnInit {
  list: any;
  exploreConnections: any;
  constructor(
    private location: Location,
    private router: Router,
    private networkService: NetworkService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.network();
  }

  network(): void {
    this.exploreConnections = undefined;
    this.networkService.stream().subscribe((data) => {
      console.log(data);
      this.list = data;
    });
  }

  explore(): void {
    this.location.replaceState('/s/network/explore');
    this.list = undefined;
    this.networkService.explore().subscribe((data) => {
      console.log(data);
      this.exploreConnections = data;
    });
  }
}
