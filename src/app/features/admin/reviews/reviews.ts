import { Component, OnInit } from '@angular/core';
import { ReviewModel } from '../../../shared/models/review.model';
import { Review } from '../../../shared/services/review';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reviews',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './reviews.html',
  styleUrl: './reviews.css',
})
export class Reviews implements OnInit {
  
  reviews: ReviewModel[] = [];
  hotelId = 0;

  message = '';
  errorMessage = '';

  constructor(private reviewService: Review) {}

  ngOnInit(): void {
    this.loadAllReviews();
  }

  loadAllReviews(): void {
    this.reviewService.getAllReviews().subscribe({
      next: (data) => {
        this.reviews = data;
      },
      error: (err) => {
        console.error('Error loading reviews:', err);
        this.errorMessage = 'Failed to load reviews';
      }
    });
  }

  loadReviewsByHotel(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.hotelId) {
      this.errorMessage = 'Please enter a hotel ID';
      return;
    }

    this.reviewService.getReviewsByHotel(this.hotelId).subscribe({
      next: (data) => {
        this.reviews = data;
      },
      error: (err) => {
        console.error('Error loading hotel reviews:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to load hotel reviews';
      }
    });
  }

  deleteReview(id: number): void {
    this.message = '';
    this.errorMessage = '';

    this.reviewService.deleteReview(id).subscribe({
      next: () => {
        this.message = 'Review deleted successfully';
        this.loadAllReviews();
      },
      error: (err) => {
        console.error('Error deleting review:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to delete review';
      }
    });
  }

  clearFilter(): void {
    this.hotelId = 0;
    this.loadAllReviews();
  }
}


