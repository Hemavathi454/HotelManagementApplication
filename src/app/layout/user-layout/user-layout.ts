import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { Navbar } from '../navbar/navbar';

@Component({
  selector: 'app-user-layout',
  imports: [CommonModule,RouterOutlet,RouterModule,Navbar],
  templateUrl: './user-layout.html',
  styleUrl: './user-layout.css',
})
export class UserLayout {
  userLinks = [
    { label: 'Dashboard', path: '/user/dashboard' },
    { label: 'Hotels', path: '/user/hotels' },
    { label: 'Rooms', path: '/user/rooms' },
    { label: 'My Reservations', path: '/user/my-reservations' },
    { label: 'Payments', path: '/user/payments-history' },
    { label: 'Reviews', path: '/user/my-reviews' }
  ];

  constructor(public router: Router) {}

  isActive(path: string): boolean {
    return this.router.url === path;
  }

}
