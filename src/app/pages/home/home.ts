import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../core/auth/AuthService';
interface Contribution {
  label: string;
  path: string;
  isBackend?: boolean;
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
    { label: 'User My Reservations', path: '/user/my-reservations' },
     { label: 'Get All Reservations', path: 'http://localhost:8080/reservations', isBackend: true },
        { label: 'Create Reservation', path: '/user/booking', isBackend: false },
        { label: 'Get Reservation By ID', path: 'http://localhost:8080/reservations/1', isBackend: true },
        { label: 'Delete Reservation', path: '/user/my-reservations', isBackend: false },
        { label: 'Update Reservation', path: '/admin/reservations', isBackend: false },

        { label: 'Get All Payments', path: 'http://localhost:8080/payments', isBackend: true },
        { label: 'Create Payment', path: '/user/payments-history', isBackend: false },
        { label: 'Get Payment By ID', path: 'http://localhost:8080/payments/1', isBackend: true },
        { label: 'Update Payment Status', path: '/admin/payments', isBackend: false }
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
        },
        { label: 'Reservations By Room', path: 'http://localhost:8080/reservations/rooms/1/reservations', isBackend: true },
        { label: 'Reservations By Guest Email', path: 'http://localhost:8080/reservations/guest-email/Ssrinithi.0508@gmail.com', isBackend: true },
        { label: 'Payments By Reservation', path: 'http://localhost:8080/payments/reservations/1/payments', isBackend: true },

        { label: 'Get All Users', path: 'http://localhost:8080/users', isBackend: true },
        { label: 'Get User By ID', path: 'http://localhost:8080/users/1', isBackend: true },
        { label: 'Create User', path: '/admin/users', isBackend: false },
        { label: 'Update User', path: '/admin/users', isBackend: false },
        { label: 'Delete User', path: '/admin/users', isBackend: false },

        { label: 'Create Amenity', path: '/admin/amenities', isBackend: false }
      ]
    },
    {
      name: 'Hemavathi V',
      initials: 'HV',
      contributions: [
        { label: 'Admin Hotels', path: '/admin/hotels' },
        { label: 'User Hotel List', path: '/user/hotels' },
        { label: 'User Booking', path: '/user/booking' },
         { label: 'Get All Hotels', path: 'http://localhost:8080/hotels', isBackend: true },
        { label: 'Create Hotel', path: '/admin/hotels', isBackend: false },
        { label: 'Get Hotel By ID', path: 'http://localhost:8080/hotels/1', isBackend: true },
        { label: 'Update Hotel', path: '/admin/hotels', isBackend: false },
        { label: 'Delete Hotel', path: '/admin/hotels', isBackend: false },

        { label: 'Get All Amenities', path: 'http://localhost:8080/amenities', isBackend: true },
        { label: 'Get Amenity By ID', path: 'http://localhost:8080/amenities/1', isBackend: true },
        { label: 'Update Amenity', path: '/admin/amenities', isBackend: false },
        { label: 'Delete Amenity', path: '/admin/amenities', isBackend: false }
      ]
    },
    {
      name: 'Kaviya Priya R',
      initials: 'KP',
      contributions: [
        { label: 'Admin Rooms', path: '/admin/rooms' },
        { label: 'User Room List', path: '/user/rooms' },
        { label: 'Admin Room Types', path: '/admin/room-types' },
        { label: 'Create Room', path: '/admin/rooms', isBackend: false },
        { label: 'Get Room By ID', path: 'http://localhost:8080/rooms/1', isBackend: true },
        { label: 'Get All Rooms', path: 'http://localhost:8080/rooms', isBackend: true },
        { label: 'Update Room', path: '/admin/rooms', isBackend: false },
        { label: 'Delete Room', path: '/admin/rooms', isBackend: false },
        { label: 'Update Room Availability', path: '/admin/rooms', isBackend: false },

        { label: 'Get All Room Types', path: 'http://localhost:8080/room-types', isBackend: true },
        { label: 'Create Room Type', path: '/admin/room-types', isBackend: false },
        { label: 'Delete Room Type', path: '/admin/room-types', isBackend: false }
      ]
    },
    {
      name: 'Naveena Shilvasta X',
      initials: 'NS',
      contributions: [
        { label: 'Admin Reviews', path: '/admin/reviews' },
        { label: 'User My Reviews', path: '/user/my-reviews' },
         { label: 'Get All Reviews', path: 'http://localhost:8080/reviews', isBackend: true },
        { label: 'Create Review', path: '/user/my-reviews', isBackend: false },
        { label: 'Get Review By ID', path: 'http://localhost:8080/reviews/1', isBackend: true },
        { label: 'Delete Review', path: '/admin/reviews', isBackend: false },
        { label: 'Get Review By Reservation', path: 'http://localhost:8080/reviews/reservations/1/review', isBackend: true },
        { label: 'Get Reviews By Hotel', path: 'http://localhost:8080/reviews/hotels/1/reviews', isBackend: true },

        { label: 'Get Rooms By Hotel', path: 'http://localhost:8080/rooms/hotel/1', isBackend: true },
        { label: 'Add Amenity To Room', path: '/admin/rooms', isBackend: false },
        { label: 'Remove Amenity From Room', path: '/admin/rooms', isBackend: false }
      ]
    }
  ];

  constructor(private router: Router) {}

  toggleTeam(): void {
    this.showTeam = !this.showTeam;
    if (!this.showTeam) {
      this.selectedMember = null;
    }
  }

  selectMember(member: TeamMember): void {
    this.selectedMember = member;
  }

  openContribution(path: string, isBackend: boolean = false): void {
  if (isBackend || path.startsWith('http://') || path.startsWith('https://')) {
    window.open(path, '_blank');
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


