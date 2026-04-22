import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RoomModel } from '../models/room.model';

@Injectable({
  providedIn: 'root',
})
export class Room {
  private apiUrl = 'http://localhost:8080/rooms';

  constructor(private http: HttpClient) {}

  addRoom(data: RoomModel): Observable<RoomModel> {
    return this.http.post<RoomModel>(this.apiUrl, data);
  }

  getAllRooms(): Observable<RoomModel[]> {
    return this.http.get<RoomModel[]>(this.apiUrl);
  }

  getRoomById(roomId: number): Observable<RoomModel> {
    return this.http.get<RoomModel>(`${this.apiUrl}/${roomId}`);
  }

  getRoomsByAvailability(available: boolean): Observable<RoomModel[]> {
    const params = new HttpParams().set('available', available);
    return this.http.get<RoomModel[]>(this.apiUrl, { params });
  }

  getRoomsByRoomType(roomTypeId: number): Observable<RoomModel[]> {
    const params = new HttpParams().set('roomTypeId', roomTypeId);
    return this.http.get<RoomModel[]>(this.apiUrl, { params });
  }

  getRoomsByHotel(hotelId: number): Observable<RoomModel[]> {
    return this.http.get<RoomModel[]>(`${this.apiUrl}/hotel/${hotelId}`);
  }

  updateRoom(roomId: number, data: RoomModel): Observable<RoomModel> {
    return this.http.put<RoomModel>(`${this.apiUrl}/${roomId}`, data);
  }

  deleteRoom(roomId: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${roomId}`, { responseType: 'text' });
  }

  updateRoomAvailability(roomId: number, available: boolean): Observable<RoomModel> {
    const params = new HttpParams().set('available', available);
    return this.http.patch<RoomModel>(`${this.apiUrl}/${roomId}/availability`, {}, { params });
  }

  addAmenityToRoom(roomId: number, amenityId: number): Observable<RoomModel> {
    return this.http.post<RoomModel>(`${this.apiUrl}/${roomId}/amenities/${amenityId}`, {});
  }

  removeAmenityFromRoom(roomId: number, amenityId: number): Observable<RoomModel> {
    return this.http.delete<RoomModel>(`${this.apiUrl}/${roomId}/amenities/${amenityId}`);
  }

  getAmenitiesByRoom(roomId: number): Observable<number[]> {
    return this.http.get<number[]>(`${this.apiUrl}/${roomId}/amenities`);
  }
  
}
