import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HotelModel } from '../models/hotel.model';

@Injectable({
  providedIn: 'root',
})
export class Hotel {
   private apiUrl = 'http://localhost:8080/hotels';

  constructor(private http: HttpClient
    
  ) {}

  getAllHotels(): Observable<HotelModel[]> {
    return this.http.get<HotelModel[]>(this.apiUrl);
  }

  getHotelsByLocation(location: string): Observable<HotelModel[]> {
    const params = new HttpParams().set('location', location);
    return this.http.get<HotelModel[]>(this.apiUrl, { params });
  }

  getHotelById(hotelId: number): Observable<HotelModel> {
    return this.http.get<HotelModel>(`${this.apiUrl}/${hotelId}`);
  }

  addHotel(data: HotelModel): Observable<HotelModel> {
    return this.http.post<HotelModel>(this.apiUrl, data);
  }

  updateHotel(hotelId: number, data: HotelModel): Observable<HotelModel> {
    return this.http.put<HotelModel>(`${this.apiUrl}/${hotelId}`, data);
  }

  deleteHotel(hotelId: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${hotelId}`, { responseType: 'text' });
  }
  
  
}
