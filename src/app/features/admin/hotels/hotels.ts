import { Component, OnInit } from '@angular/core';

 import { Hotel } from './../../../shared/services/hotel';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HotelModel } from '../../../shared/models/hotel.model';

@Component({
  selector: 'app-hotels',
  imports: [FormsModule,CommonModule],
 

  standalone: true,
templateUrl: './hotels.html',
  styleUrl: './hotels.css',
})
export class Hotels implements OnInit {
   hotels: HotelModel[] = [];

  hotelForm: HotelModel = {
    name: '',
    location: '',
    description: '',
    amenityIds: []
  };

  editingHotelId: number | null = null;
  message = '';
  errorMessage = '';

  constructor(private hotelService: Hotel) {}

  ngOnInit(): void {
    this.loadHotels();
  }

  loadHotels(): void {
    this.hotelService.getAllHotels().subscribe({
      next: (data: HotelModel[]) => {
        this.hotels = data;
      },
      error: (err) => {
        console.error('Error loading hotels:', err);
        this.errorMessage = 'Failed to load hotels';
      }
    });
  }

  saveHotel(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.editingHotelId !== null) {
      this.hotelService.updateHotel(this.editingHotelId, this.hotelForm).subscribe({
        next: () => {
          this.message = 'Hotel updated successfully';
          this.resetForm();
          this.loadHotels();
        },
        error: (err) => {
          console.error('Error updating hotel:', err);
          this.errorMessage = 'Failed to update hotel';
        }
      });
    } else {
      this.hotelService.addHotel(this.hotelForm).subscribe({
        next: () => {
          this.message = 'Hotel added successfully';
          this.resetForm();
          this.loadHotels();
        },
        error: (err) => {
          console.error('Error adding hotel:', err);
          this.errorMessage = 'Failed to add hotel';
        }
      });
    }
  }

  editHotel(hotel: HotelModel): void {
    this.editingHotelId = hotel.id ?? null;

    this.hotelForm = {
      id: hotel.id,
      name: hotel.name,
      location: hotel.location,
      description: hotel.description,
      amenityIds: hotel.amenityIds ?? [],
      roomIds: hotel.roomIds ?? []
    };
  }

  deleteHotel(hotelId: number): void {
    this.message = '';
    this.errorMessage = '';

    this.hotelService.deleteHotel(hotelId).subscribe({
      next: () => {
        this.message = 'Hotel deleted successfully';
        this.loadHotels();
      },
      error: (err) => {
        console.error('Error deleting hotel:', err);
        this.errorMessage = 'Failed to delete hotel';
      }
    });
  }

  resetForm(): void {
    this.hotelForm = {
      name: '',
      location: '',
      description: '',
      amenityIds: []
    };
    this.editingHotelId = null;
  }
}

