import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../core/auth/AuthService';
interface Contribution {
  label: string;
  path: string;
}

interface TeamMember {
  name: string;
  initials: string;
  contributions: Contribution[];
}

@Component({
  selector: 'app-home',
  imports: [CommonModule,FormsModule,RouterModule],
    standalone: true,
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
   showTeam = false;
  selectedMember: TeamMember | null = null;

  teamMembers: TeamMember[] = [
   {
  name: 'Ssrinithi S M',
  initials: 'SS',
  contributions: [
    { label: 'Home Page', path: '/' },
    { label: 'Admin Login', path: '/admin/login' },
    { label: 'User Login / Register', path: '/auth' },
    { label: 'User Dashboard', path: '/user/dashboard' },
    { label: 'User Payments History', path: '/user/payments-history' },
    { label: 'User My Reservations', path: '/user/my-reservations' }
  ]
},
    {
      name: 'Joyce Reshma D',
      initials: 'JR',
      contributions: [
        { label: 'Admin Dashboard', path: '/admin/dashboard' },
        { label: 'Admin Payments', path: '/admin/payments' },
        { label: 'Admin Amenities', path: '/admin/amenities' },
        {label:'Admin Reservations', path:'/admin/reservations'
        }
      ]
    },
    {
      name: 'Hemavathi V',
      initials: 'HV',
      contributions: [
        { label: 'Admin Hotels', path: '/admin/hotels' },
        { label: 'User Hotel List', path: '/user/hotels' },
        { label: 'User Booking', path: '/user/booking' }
      ]
    },
    {
      name: 'Kaviya Priya R',
      initials: 'KP',
      contributions: [
        { label: 'Admin Rooms', path: '/admin/rooms' },
        { label: 'User Room List', path: '/user/rooms' },
        { label: 'Admin Room Types', path: '/admin/room-types' }
      ]
    },
    {
      name: 'Naveena Shilvasta X',
      initials: 'NS',
      contributions: [
        { label: 'Admin Reviews', path: '/admin/reviews' },
        { label: 'User My Reviews', path: '/user/my-reviews' }
      ]
    }
  ];

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  toggleTeam(): void {
    this.showTeam = !this.showTeam;

    if (!this.showTeam) {
      this.selectedMember = null;
    }
  }

  selectMember(member: TeamMember): void {
    this.selectedMember = member;
  }

  openContribution(path: string): void {
  if (path === '/about' || path === '/contact') {
    this.router.navigate([path]);
    return;
  }

  if (path === '/admin/login') {
    this.router.navigate(['/admin/login']);
    return;
  }

  if (path === '/auth') {
    this.router.navigate(['/auth']);
    return;
  }

  if (path.startsWith('/admin')) {
    this.router.navigate(['/admin/login'], {
      queryParams: { returnUrl: path }
    });
    return;
  }

  if (path.startsWith('/user')) {
    this.router.navigate(['/auth'], {
      queryParams: { returnUrl: path }
    });
    return;
  }

  this.router.navigate([path]);
}

}
