import { Component } from '@angular/core';
import { ReservationModel } from '../../../shared/models/reservation.model';
import { Reservation } from '../../../shared/services/reservation';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reservations',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './reservations.html',
  styleUrl: './reservations.css',
})
export class Reservations {
   reservations: ReservationModel[] = [];

  reservationForm: ReservationModel = {
    guestName: '',
    guestEmail: '',
    guestPhone: '',
    checkInDate: '',
    checkOutDate: '',
    roomId: 0,
    userId: 0
  };

  editingReservationId: number | null = null;
  searchRoomId = 0;
  searchGuestEmail = '';

  message = '';
  errorMessage = '';

  constructor(private reservationService: Reservation) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getAllReservations().subscribe({
      next: (data) => {
        this.reservations = data;
      },
      error: (err) => {
        console.error('Error loading reservations:', err);
        this.errorMessage = 'Failed to load reservations';
      }
    });
  }

  editReservation(reservation: ReservationModel): void {
    this.editingReservationId = reservation.id ?? null;
    this.reservationForm = {
      id: reservation.id,
      guestName: reservation.guestName,
      guestEmail: reservation.guestEmail,
      guestPhone: reservation.guestPhone,
      checkInDate: reservation.checkInDate,
      checkOutDate: reservation.checkOutDate,
      roomId: reservation.roomId,
      userId: reservation.userId
    };
  }

  updateReservation(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.editingReservationId === null) {
      this.errorMessage = 'Please select a reservation to edit';
      return;
    }

    this.reservationService.updateReservation(this.editingReservationId, this.reservationForm).subscribe({
      next: () => {
        this.message = 'Reservation updated successfully';
        this.resetForm();
        this.loadReservations();
      },
      error: (err) => {
        console.error('Error updating reservation:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to update reservation';
      }
    });
  }

  deleteReservation(id: number): void {
    this.message = '';
    this.errorMessage = '';

    this.reservationService.deleteReservation(id).subscribe({
      next: () => {
        this.message = 'Reservation deleted successfully';
        this.loadReservations();
      },
      error: (err) => {
        console.error('Error deleting reservation:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to delete reservation';
      }
    });
  }

  searchByRoom(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.searchRoomId) {
      this.errorMessage = 'Please enter a room ID';
      return;
    }

    this.reservationService.getReservationsByRoom(this.searchRoomId).subscribe({
      next: (data) => {
        this.reservations = data;
      },
      error: (err) => {
        console.error('Error searching reservations by room:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to search reservations';
      }
    });
  }

  searchByGuestEmail(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.searchGuestEmail.trim()) {
      this.errorMessage = 'Please enter a guest email';
      return;
    }

    this.reservationService.getReservationsByGuestEmail(this.searchGuestEmail).subscribe({
      next: (data) => {
        this.reservations = data;
      },
      error: (err) => {
        console.error('Error searching reservations by email:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to search reservations';
      }
    });
  }

  clearFilters(): void {
    this.searchRoomId = 0;
    this.searchGuestEmail = '';
    this.loadReservations();
  }

  resetForm(): void {
    this.reservationForm = {
      guestName: '',
      guestEmail: '',
      guestPhone: '',
      checkInDate: '',
      checkOutDate: '',
      roomId: 0,
      userId: 0
    };
    this.editingReservationId = null;
  }

}
