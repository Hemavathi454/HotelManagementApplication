import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [CommonModule,RouterModule],
  standalone:true,
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {
  adminLinks = [
    { label: 'Dashboard', path: '/admin/dashboard' },
    { label: 'Users', path: '/admin/users' },
    { label: 'Hotels', path: '/admin/hotels' },
    { label: 'Rooms', path: '/admin/rooms' },
    { label: 'Room Types', path: '/admin/room-types' },
    { label: 'Amenities', path: '/admin/amenities' },
    { label: 'Reservations', path: '/admin/reservations' },
    { label: 'Payments', path: '/admin/payments' },
    { label: 'Reviews', path: '/admin/reviews' }
  ];

  constructor(public router: Router) {}

  isActive(path: string): boolean {
    return this.router.url === path;
  }

}
