import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReviewModel } from '../models/review.model';

@Injectable({
  providedIn: 'root',
})
export class Review {
  private apiUrl = 'http://localhost:8080/reviews';

  constructor(private http: HttpClient) {}

  addReview(data: ReviewModel): Observable<ReviewModel> {
    return this.http.post<ReviewModel>(this.apiUrl, data);
  }

  getAllReviews(): Observable<ReviewModel[]> {
    return this.http.get<ReviewModel[]>(this.apiUrl);
  }

  getReviewById(id: number): Observable<ReviewModel> {
    return this.http.get<ReviewModel>(`${this.apiUrl}/${id}`);
  }

  getReviewByReservation(reservationId: number): Observable<ReviewModel> {
    return this.http.get<ReviewModel>(`${this.apiUrl}/reservations/${reservationId}/review`);
  }

  getReviewsByHotel(hotelId: number): Observable<ReviewModel[]> {
    return this.http.get<ReviewModel[]>(`${this.apiUrl}/hotels/${hotelId}/reviews`);
  }

  deleteReview(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }
  
}
