import { Component } from '@angular/core';
import { UserModel } from '../../../shared/models/user.model';
import { Hotel } from '../../../shared/services/hotel';
import { Room } from '../../../shared/services/room';
import { Reservation } from '../../../shared/services/reservation';
import {  AuthService } from '../../../core/auth/AuthService';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HotelModel } from '../../../shared/models/hotel.model';
import { ReservationModel } from '../../../shared/models/reservation.model';
import { Payment } from '../../../shared/services/payment';
import { PaymentModel } from '../../../shared/models/payment.model';

@Component({
  selector: 'app-userdashboard',
  imports: [CommonModule,FormsModule],
  templateUrl: './UserDashboard.html',
  styleUrl: './UserDashboard.css',
})
export class UserDashboard {
  currentUser: UserModel | null = null;

  totalHotels = 0;
  availableRooms = 0;
  myReservations = 0;
  pendingPayments = 0;

  featuredHotels: HotelModel[] = [];
  latestReservation: ReservationModel | null = null;

  errorMessage = '';

  constructor(
    private hotelService: Hotel,
    private roomService: Room,
    private reservationService: Reservation,
    private paymentService: Payment,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();
    this.loadDashboardData();
  }

  loadDashboardData(): void {
    this.hotelService.getAllHotels().subscribe({
      next: (data) => {
        this.totalHotels = data.length;
        this.featuredHotels = data.slice(0, 3);
      },
      error: (err) => {
        console.error('Error loading hotels:', err);
        this.errorMessage = 'Failed to load dashboard data';
      }
    });

    this.roomService.getRoomsByAvailability(true).subscribe({
      next: (data) => {
        this.availableRooms = data.length;
      },
      error: (err) => {
        console.error('Error loading available rooms:', err);
      }
    });

    if (this.currentUser?.email) {
      this.reservationService.getReservationsByGuestEmail(this.currentUser.email).subscribe({
        next: (data) => {
          this.myReservations = data.length;

          if (data.length > 0) {
            this.latestReservation = data[data.length - 1];
          }

          this.loadPendingPayments(data);
        },
        error: (err) => {
          console.error('Error loading reservations:', err);
        }
      });
    }
  }

  loadPendingPayments(reservations: ReservationModel[]): void {
    this.pendingPayments = 0;

    reservations.forEach((reservation) => {
      if (reservation.id != null) {
        this.paymentService.getPaymentsByReservation(reservation.id).subscribe({
          next: (payments: PaymentModel[]) => {
            if (payments.length === 0) {
              this.pendingPayments++;
            } else {
              const latestPayment = payments[payments.length - 1];
              if (latestPayment.paymentStatus === 'PENDING' || latestPayment.paymentStatus === 'FAILED') {
                this.pendingPayments++;
              }
            }
          },
          error: () => {
            this.pendingPayments++;
          }
        });
      }
    });
  }

  goTo(path: string): void {
    this.router.navigate([path]);
  }

  bookHotel(hotelId: number): void {
    this.router.navigate(['/user/rooms'], {
      queryParams: { hotelId }
    });
  }
}
