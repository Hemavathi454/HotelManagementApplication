import { Component } from '@angular/core';
import { UserModel } from '../../../shared/models/user.model';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/auth/AuthService';
import { UserService } from '../../../shared/services/user';
import { Hotel } from '../../../shared/services/hotel';
import { Room } from '../../../shared/services/room';
import { RoomType } from '../../../shared/services/room-type';
import { Amenity } from '../../../shared/services/amenity';
import { Reservation } from '../../../shared/services/reservation';
import { Payment } from '../../../shared/services/payment';
import { Review } from '../../../shared/services/review';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ReservationModel } from '../../../shared/models/reservation.model';
import { PaymentModel } from '../../../shared/models/payment.model';

@Component({
  selector: 'app-admindashboard',
  imports: [CommonModule,FormsModule],
  templateUrl: './AdminDashboard.html',
  styleUrl: './Admindashboard.css',
})
export class AdminDashboard {
   currentUser: UserModel | null = null;

  totalUsers = 0;
  totalHotels = 0;
  totalRooms = 0;
  totalRoomTypes = 0;
  totalAmenities = 0;
  totalReservations = 0;
  totalPayments = 0;
  totalReviews = 0;
  pendingPayments = 0;

  recentReservations: ReservationModel[] = [];
  recentPayments: PaymentModel[] = [];

  errorMessage = '';

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private hotelService: Hotel,
    private roomService: Room,
    private roomTypeService: RoomType,
    private amenityService: Amenity,
    private reservationService: Reservation,
    private paymentService: Payment,
    private reviewService: Review,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
    this.loadDashboardCounts();
  }

  goTo(path: string): void {
    this.router.navigate([path]);
  }

  loadDashboardCounts(): void {
    this.userService.getAllUsers().subscribe({
      next: (data) => this.totalUsers = data.length,
      error: (err) => {
        console.error('Error loading users count:', err);
        this.errorMessage = 'Failed to load dashboard data';
      }
    });

    this.hotelService.getAllHotels().subscribe({
      next: (data) => this.totalHotels = data.length,
      error: (err) => console.error('Error loading hotels count:', err)
    });

    this.roomService.getAllRooms().subscribe({
      next: (data) => this.totalRooms = data.length,
      error: (err) => console.error('Error loading rooms count:', err)
    });

    this.roomTypeService.getAllRoomTypes().subscribe({
      next: (data) => this.totalRoomTypes = data.length,
      error: (err) => console.error('Error loading room types count:', err)
    });

    this.amenityService.getAllAmenities().subscribe({
      next: (data) => this.totalAmenities = data.length,
      error: (err) => console.error('Error loading amenities count:', err)
    });

    this.reservationService.getAllReservations().subscribe({
      next: (data) => {
        this.totalReservations = data.length;
        this.recentReservations = data.slice(-5).reverse();
      },
      error: (err) => console.error('Error loading reservations count:', err)
    });

    this.paymentService.getAllPayments().subscribe({
      next: (data) => {
        this.totalPayments = data.length;
        this.recentPayments = data.slice(-5).reverse();
        this.pendingPayments = data.filter(
          payment => payment.paymentStatus === 'PENDING' || payment.paymentStatus === 'FAILED'
        ).length;
      },
      error: (err) => console.error('Error loading payments count:', err)
    });

    this.reviewService.getAllReviews().subscribe({
      next: (data) => this.totalReviews = data.length,
      error: (err) => console.error('Error loading reviews count:', err)
    });
  }
}
