import { Component, Input } from '@angular/core';
import { AuthService } from './../../core/auth/AuthService';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-navbar',
  imports: [CommonModule,FormsModule],
  standalone:true,
  
templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  @Input() title = 'Hotel Booking System';
  @Input() showHomeButton = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  goHome(): void {
    const user = this.authService.getUser();

    if (user?.role === 'ADMIN') {
      this.router.navigate(['/admin/dashboard']);
    } else if (user?.role === 'CUSTOMER') {
      this.router.navigate(['/user/dashboard']);
    } else {
      this.router.navigate(['/']);
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
