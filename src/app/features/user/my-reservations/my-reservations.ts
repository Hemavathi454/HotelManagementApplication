import { Component, OnInit } from '@angular/core';
import { ReservationModel } from '../../../shared/models/reservation.model';
import { UserModel } from '../../../shared/models/user.model';
import { Reservation } from '../../../shared/services/reservation';
import { AuthService } from '../../../core/auth/AuthService';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Payment} from '../../../shared/services/payment';
import { PaymentModel } from '../../../shared/models/payment.model';
import { Router } from '@angular/router';
import { ReviewModel } from '../../../shared/models/review.model';
import { Review } from '../../../shared/services/review';
 interface ReservationWithExtras extends ReservationModel {
  paymentStatus?: string;
  hasReview?: boolean;
  review?: ReviewModel | null;
}

@Component({
  selector: 'app-my-reservations',
  imports: [CommonModule,FormsModule],
  templateUrl: './my-reservations.html',
  styleUrl: './my-reservations.css',
})
export class MyReservations implements OnInit {

  reservations: ReservationWithExtras[] = [];
  currentUser: UserModel | null = null;
  errorMessage = '';
  message = '';

  constructor(
    private reservationService: Reservation,
    private paymentService: Payment,
    private reviewService: Review,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();

    if (this.currentUser?.email) {
      this.loadMyReservations(this.currentUser.email);
    } else {
      this.errorMessage = 'User email not found';
    }
  }

  loadMyReservations(email: string): void {
    this.errorMessage = '';
    this.message = '';

    this.reservationService.getReservationsByGuestEmail(email).subscribe({
      next: (data) => {
        this.reservations = data.map((reservation) => ({
          ...reservation,
          paymentStatus: 'PENDING',
          hasReview: false,
          review: null
        }));

        this.loadPaymentStatuses();
        this.loadReviewStatuses();
      },
      error: (err) => {
        console.error('Error loading reservations:', err);
        this.errorMessage = 'Failed to load reservations';
      }
    });
  }

  loadPaymentStatuses(): void {
    this.reservations.forEach((reservation) => {
      if (reservation.id != null) {
        this.paymentService.getPaymentsByReservation(reservation.id).subscribe({
          next: (payments: PaymentModel[]) => {
            if (payments.length > 0) {
              const latestPayment = payments[payments.length - 1];
              reservation.paymentStatus = latestPayment.paymentStatus;
            } else {
              reservation.paymentStatus = 'PENDING';
            }
          },
          error: () => {
            reservation.paymentStatus = 'PENDING';
          }
        });
      }
    });
  }

  loadReviewStatuses(): void {
    this.reservations.forEach((reservation) => {
      if (reservation.id != null) {
        this.reviewService.getReviewByReservation(reservation.id).subscribe({
          next: (review: ReviewModel) => {
            reservation.hasReview = true;
            reservation.review = review;
          },
          error: () => {
            reservation.hasReview = false;
            reservation.review = null;
          }
        });
      }
    });
  }

  makePayment(reservationId: number): void {
    this.router.navigate(['/user/payments-history'], {
      queryParams: { reservationId }
    });
  }

  addReview(reservationId: number): void {
    this.router.navigate(['/user/my-reviews'], {
      queryParams: { reservationId }
    });
  }

  viewReview(reservationId: number): void {
    this.router.navigate(['/user/my-reviews'], {
      queryParams: { reservationId, view: true }
    });
  }

  cancelReservation(reservationId: number): void {
    this.message = '';
    this.errorMessage = '';

    this.reservationService.deleteReservation(reservationId).subscribe({
      next: () => {
        this.message = 'Reservation cancelled successfully';
        if (this.currentUser?.email) {
          this.loadMyReservations(this.currentUser.email);
        }
      },
      error: (err) => {
        console.error('Error cancelling reservation:', err);
        this.errorMessage =
          err?.error?.message ||
          err?.error ||
          'Failed to cancel reservation';
      }
    });
  }
}