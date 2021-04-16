import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @ViewChild('username') username: ElementRef;
  @ViewChild('password') password: ElementRef;

	constructor(private router: Router, private authService: AuthService) {
	}

	ngOnInit(): void {
	}

	auth(): void {
    this.username = this.username.nativeElement.value;
    this.password = this.password.nativeElement.value;
		const user = {
			username: this.username,
			password: this.password
		};
		this.authService.auth(user).subscribe((auth) => {
			sessionStorage.setItem('jwt', auth.headers.get('authorization'));
			this.router.navigate(['/s/dashboard']);
		});
	}
}
