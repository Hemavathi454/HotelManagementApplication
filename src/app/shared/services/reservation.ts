import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReservationModel } from '../models/reservation.model';

@Injectable({
  providedIn: 'root',
})
export class Reservation {
  private apiUrl = 'http://localhost:8080/reservations';

  constructor(private http: HttpClient) {}

  createReservation(data: ReservationModel): Observable<ReservationModel> {
    return this.http.post<ReservationModel>(this.apiUrl, data);
  }

  getAllReservations(): Observable<ReservationModel[]> {
    return this.http.get<ReservationModel[]>(this.apiUrl);
  }

  getReservationById(reservationId: number): Observable<ReservationModel> {
    return this.http.get<ReservationModel>(`${this.apiUrl}/${reservationId}`);
  }

  getReservationsByRoom(roomId: number): Observable<ReservationModel[]> {
    return this.http.get<ReservationModel[]>(`${this.apiUrl}/rooms/${roomId}/reservations`);
  }

  getReservationsByGuestEmail(email: string): Observable<ReservationModel[]> {
    return this.http.get<ReservationModel[]>(`${this.apiUrl}/guest-email/${email}`);
  }

  updateReservation(reservationId: number, data: ReservationModel): Observable<ReservationModel> {
    return this.http.put<ReservationModel>(`${this.apiUrl}/${reservationId}`, data);
  }

  deleteReservation(reservationId: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${reservationId}`, { responseType: 'text' });
  }
  
}
