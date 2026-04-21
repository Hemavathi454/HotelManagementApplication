import { Component, OnInit } from '@angular/core';
import { HotelModel } from '../../../shared/models/hotel.model';
import { Hotel } from '../../../shared/services/hotel';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hotel-list',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './hotel-list.html',
  styleUrl: './hotel-list.css',
})
export class HotelList implements OnInit {
    hotels: HotelModel[] = [];
  searchLocation = '';
  errorMessage = '';

  constructor(
    private hotelService: Hotel,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadHotels();
  }

  loadHotels(): void {
    this.errorMessage = '';

    this.hotelService.getAllHotels().subscribe({
      next: (data) => {
        this.hotels = data;
      },
      error: (err) => {
        console.error('Error loading hotels:', err);
        this.errorMessage = 'Failed to load hotels';
      }
    });
  }

  searchByLocation(): void {
    this.errorMessage = '';

    if (!this.searchLocation.trim()) {
      this.loadHotels();
      return;
    }

    this.hotelService.getHotelsByLocation(this.searchLocation).subscribe({
      next: (data) => {
        this.hotels = data;
      },
      error: (err) => {
        console.error('Error searching hotels:', err);
        this.errorMessage = 'Failed to search hotels';
      }
    });
  }

  clearSearch(): void {
    this.searchLocation = '';
    this.loadHotels();
  }

  bookNow(hotelId: number): void {
    this.router.navigate(['/user/rooms'], {
      queryParams: { hotelId }
    });
  

}


}