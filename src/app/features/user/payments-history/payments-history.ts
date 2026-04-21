import { Component } from '@angular/core';
import { PaymentModel } from '../../../shared/models/payment.model';
import { Payment } from '../../../shared/services/payment';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationModel } from '../../../shared/models/reservation.model';
import { RoomModel } from '../../../shared/models/room.model';
import { RoomTypeModel } from '../../../shared/models/room-type.model';
import { Reservation } from '../../../shared/services/reservation';
import { Room } from '../../../shared/services/room';
import { RoomType } from '../../../shared/services/room-type';
@Component({
  selector: 'app-payments-history',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './payments-history.html',
  styleUrl: './payments-history.css',
})
export class PaymentsHistory {
 paymentForm: PaymentModel = {
    amount: 0,
    paymentDate: '',
    paymentStatus: 'PAID',
    reservationId: 0
  };

  reservationSearchId = 0;
  payments: PaymentModel[] = [];

  message = '';
  errorMessage = '';
  paymentSuccess = false;

  reservationDetails: ReservationModel | null = null;
  roomDetails: RoomModel | null = null;
  roomTypeDetails: RoomTypeModel | null = null;
  totalNights = 0;

  constructor(
    private paymentService: Payment,
    private reservationService: Reservation,
    private roomService: Room,
    private roomTypeService: RoomType,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const reservationId = Number(params['reservationId']);

      if (reservationId) {
        this.paymentForm.reservationId = reservationId;
        this.reservationSearchId = reservationId;
        this.loadReservationPricing(reservationId);
        this.loadPaymentsByReservation();
      }
    });
  }

  loadReservationPricing(reservationId: number): void {
    this.reservationService.getReservationById(reservationId).subscribe({
      next: (reservation) => {
        this.reservationDetails = reservation;
        this.totalNights = this.calculateNights(
          reservation.checkInDate,
          reservation.checkOutDate
        );

        this.roomService.getRoomById(reservation.roomId).subscribe({
          next: (room) => {
            this.roomDetails = room;

            this.roomTypeService.getRoomTypeById(room.roomTypeId).subscribe({
              next: (roomType) => {
                this.roomTypeDetails = roomType;
                this.paymentForm.amount = this.totalNights * roomType.pricePerNight;
              },
              error: (err) => {
                console.error('Error loading room type:', err);
              }
            });
          },
          error: (err) => {
            console.error('Error loading room:', err);
          }
        });
      },
      error: (err) => {
        console.error('Error loading reservation:', err);
      }
    });
  }

  calculateNights(checkInDate: string, checkOutDate: string): number {
    const checkIn = new Date(checkInDate);
    const checkOut = new Date(checkOutDate);
    const diffTime = checkOut.getTime() - checkIn.getTime();
    const diffDays = diffTime / (1000 * 60 * 60 * 24);
    return diffDays > 0 ? diffDays : 1;
  }

  createPayment(): void {
    this.message = '';
    this.errorMessage = '';
    this.paymentSuccess = false;

    this.paymentService.createPayment(this.paymentForm).subscribe({
      next: () => {
        this.message = 'Payment created successfully';
        this.paymentSuccess = true;
        this.loadPaymentsByReservation();
      },
      error: (err) => {
        console.error('Error creating payment:', err);
        this.errorMessage =
          err?.error?.message ||
          err?.error ||
          'Failed to create payment';
      }
    });
  }

  loadPaymentsByReservation(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.reservationSearchId) {
      this.errorMessage = 'Please enter a reservation ID';
      return;
    }

    this.paymentService.getPaymentsByReservation(this.reservationSearchId).subscribe({
      next: (data) => {
        this.payments = data;
      },
      error: (err) => {
        console.error('Error loading payments:', err);
        this.errorMessage =
          err?.error?.message ||
          err?.error ||
          'Failed to load payments';
      }
    });
  }

  goToMyReservations(): void {
    this.router.navigate(['/user/my-reservations']);
  }

  goToHome(): void {
    this.router.navigate(['/user/dashboard']);
  }
}
