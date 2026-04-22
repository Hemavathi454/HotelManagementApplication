import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaymentModel } from '../models/payment.model';

@Injectable({
  providedIn: 'root',
})
export class Payment {
 
  private apiUrl = 'http://localhost:8080/payments';

  constructor(private http: HttpClient) {}

  createPayment(data: PaymentModel): Observable<PaymentModel> {
    return this.http.post<PaymentModel>(this.apiUrl, data);
  }

  getAllPayments(): Observable<PaymentModel[]> {
    return this.http.get<PaymentModel[]>(this.apiUrl);
  }

  getPaymentById(paymentId: number): Observable<PaymentModel> {
    return this.http.get<PaymentModel>(`${this.apiUrl}/${paymentId}`);
  }

  getPaymentsByReservation(reservationId: number): Observable<PaymentModel[]> {
    return this.http.get<PaymentModel[]>(`${this.apiUrl}/reservations/${reservationId}/payments`);
  }

  updatePaymentStatus(paymentId: number, status: string): Observable<PaymentModel> {
    const params = new HttpParams().set('status', status);
    return this.http.patch<PaymentModel>(`${this.apiUrl}/${paymentId}/status`, {}, { params });
  }
}
  

