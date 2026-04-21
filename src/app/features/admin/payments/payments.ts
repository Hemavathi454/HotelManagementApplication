import { Component } from '@angular/core';
import { PaymentModel } from '../../../shared/models/payment.model';
import { Payment } from '../../../shared/services/payment';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-payments',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './payments.html',
  styleUrl: './payments.css',
})
export class Payments {
  payments: PaymentModel[] = [];
  reservationSearchId = 0;

  selectedPaymentId: number | null = null;
  newStatus = 'PAID';

  message = '';
  errorMessage = '';

  constructor(private paymentService: Payment) {}

  ngOnInit(): void {
    this.loadPayments();
  }

  loadPayments(): void {
    this.paymentService.getAllPayments().subscribe({
      next: (data) => {
        this.payments = data;
      },
      error: (err) => {
        console.error('Error loading payments:', err);
        this.errorMessage = 'Failed to load payments';
      }
    });
  }

  searchByReservation(): void {
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
        console.error('Error searching payments:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to search payments';
      }
    });
  }

  clearSearch(): void {
    this.reservationSearchId = 0;
    this.loadPayments();
  }

  selectPayment(payment: PaymentModel): void {
    this.selectedPaymentId = payment.id ?? null;
    this.newStatus = payment.paymentStatus;
  }

  updateStatus(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.selectedPaymentId === null) {
      this.errorMessage = 'Please select a payment first';
      return;
    }

    this.paymentService.updatePaymentStatus(this.selectedPaymentId, this.newStatus).subscribe({
      next: () => {
        this.message = 'Payment status updated successfully';
        this.selectedPaymentId = null;
        this.newStatus = 'PAID';
        this.loadPayments();
      },
      error: (err) => {
        console.error('Error updating payment status:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to update payment status';
      }
    });
  }

}
