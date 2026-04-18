import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface RoomType {
  id: number;
  typeName: string;
  description: string;
  maxOccupancy: number;
  pricePerNight: number;
}
export interface RoomSearchFilter {
  typeName?: string;
  maxOccupancy?: number;
  maxPrice?: number;
}

export interface ReservationRequest {
  roomTypeId: number;
  guestName: string;
  checkInDate: string;
  checkOutDate: string;
  numberOfGuests: number;
}
@Injectable({
  providedIn: 'root'
})
export class RoomTypeService {

  

  private readonly http = inject(HttpClient);
  
  private readonly baseUrl = 'http://localhost:8080/room-types';
  private readonly reservationUrl = 'http://localhost:8080/reservations';

  getAllRoomTypes(): Observable<RoomType[]> {
    return this.http.get<RoomType[]>(this.baseUrl);
  }

  searchRoomTypes(filter: RoomSearchFilter): Observable<RoomType[]> {
    let params = new HttpParams();
    if (filter.typeName)     params = params.set('typeName', filter.typeName);
    if (filter.maxOccupancy) params = params.set('maxOccupancy', filter.maxOccupancy.toString());
    if (filter.maxPrice)     params = params.set('maxPrice', filter.maxPrice.toString());
    return this.http.get<RoomType[]>(`${this.baseUrl}/search`, { params });
  }

  reserveRoom(payload: ReservationRequest): Observable<any> {
    return this.http.post(this.reservationUrl, payload);
  }
}