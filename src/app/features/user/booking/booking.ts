import { Component } from '@angular/core';
import { Room } from '../../../shared/services/room';
import { Reservation } from '../../../shared/services/reservation';
import { AuthService } from '../../../core/auth/AuthService';
import { UserModel } from '../../../shared/models/user.model';
import { ReservationModel } from '../../../shared/models/reservation.model';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
 import { RoomModel } from './../../../shared/models/room.model';
import { Hotel } from '../../../shared/services/hotel';
import { HotelModel } from '../../../shared/models/hotel.model';
@Component({
  selector: 'app-booking',
  imports: [CommonModule,FormsModule],
 
standalone: true,
  templateUrl: './booking.html',
  styleUrl: './booking.css',
})
export class Booking {
  selectedHotel: HotelModel | null = null;
  selectedRoom: RoomModel | null = null;
  currentUser: UserModel | null = null;

  selectedHotelId: number | null = null;
  selectedRoomId: number | null = null;

  bookingForm: ReservationModel = {
    guestName: '',
    guestEmail: '',
    guestPhone: '',
    checkInDate: '',
    checkOutDate: '',
    roomId: 0,
    userId: 0
  };

  message = '';
  errorMessage = '';

  constructor(
    private reservationService: Reservation,
    private hotelService: Hotel,
    private roomService: Room,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getUser();

    if (this.currentUser) {
      this.bookingForm.userId = this.currentUser.id ?? 0;
      this.bookingForm.guestName = this.currentUser.name;
      this.bookingForm.guestEmail = this.currentUser.email;
    }

    this.route.queryParams.subscribe(params => {
      const hotelId = Number(params['hotelId']);
      const roomId = Number(params['roomId']);

      if (hotelId) {
        this.selectedHotelId = hotelId;
        this.loadSelectedHotel(hotelId);
      }

      if (roomId) {
        this.selectedRoomId = roomId;
        this.bookingForm.roomId = roomId;
        this.loadSelectedRoom(roomId);
      }

      if (!hotelId || !roomId) {
        this.errorMessage = 'Hotel or room not selected';
      }
    });
  }

  loadSelectedHotel(hotelId: number): void {
    this.hotelService.getHotelById(hotelId).subscribe({
      next: (data) => {
        this.selectedHotel = data;
      },
      error: (err) => {
        console.error('Error loading selected hotel:', err);
        this.errorMessage = 'Failed to load hotel details';
      }
    });
  }

  loadSelectedRoom(roomId: number): void {
    this.roomService.getRoomById(roomId).subscribe({
      next: (data) => {
        this.selectedRoom = data;
      },
      error: (err) => {
        console.error('Error loading selected room:', err);
        this.errorMessage = 'Failed to load room details';
      }
    });
  }

  makeReservation(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.bookingForm.roomId || !this.bookingForm.userId) {
      this.errorMessage = 'Room or user details are missing';
      return;
    }

    this.reservationService.createReservation(this.bookingForm).subscribe({
      next: () => {
        this.message = 'Reservation created successfully';
        this.router.navigate(['/user/my-reservations']);
      },
      error: (err) => {
        console.error('Error creating reservation:', err);
        this.errorMessage =
          err?.error?.message ||
          err?.error ||
          'Failed to create reservation';
      }
    });
  }
}