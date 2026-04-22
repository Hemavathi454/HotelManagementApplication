import { Component } from '@angular/core';
import { ReviewModel } from '../../../shared/models/review.model';
import { UserModel } from '../../../shared/models/user.model';
import { Review } from '../../../shared/services/review';
import {  AuthService } from '../../../core/auth/AuthService';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-my-reviews',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './my-reviews.html',
  styleUrl: './my-reviews.css',
})
export class MyReviews {
    currentUser: UserModel | null = null;

  reviewForm: ReviewModel = {
    rating: 0,
    comment: '',
    reviewDate: '',
    reservationId: 0,
    userId: 0
  };

  reservationSearchId = 0;
  reservationReview: ReviewModel | null = null;

  message = '';
  errorMessage = '';

  constructor(
    private reviewService: Review,
    private authService: AuthService
  ) {
    this.currentUser = this.authService.getUser();

    if (this.currentUser) {
      this.reviewForm.userId = this.currentUser.id ?? 0;
    }
  }

  addReview(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.reviewForm.userId) {
      this.errorMessage = 'User not found. Please login again.';
      return;
    }

    this.reviewService.addReview(this.reviewForm).subscribe({
      next: () => {
        this.message = 'Review added successfully';
        this.resetForm();
      },
      error: (err) => {
        console.error('Error adding review:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to add review';
      }
    });
  }

  getReviewByReservation(): void {
    this.message = '';
    this.errorMessage = '';
    this.reservationReview = null;

    if (!this.reservationSearchId) {
      this.errorMessage = 'Please enter a reservation ID';
      return;
    }

    this.reviewService.getReviewByReservation(this.reservationSearchId).subscribe({
      next: (data) => {
        this.reservationReview = data;
      },
      error: (err) => {
        console.error('Error fetching review:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to fetch review';
      }
    });
  }

  resetForm(): void {
    this.reviewForm = {
      rating: 0,
      comment: '',
      reviewDate: '',
      reservationId: 0,
      userId: this.currentUser?.id ?? 0
    };
  }


}
