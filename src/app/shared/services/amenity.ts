import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AmenityModel } from '../models/amenity.model';

@Injectable({
  providedIn: 'root',
})
export class Amenity {
  private apiUrl = 'http://localhost:8080/amenities';

  constructor(private http: HttpClient) {}

  getAllAmenities(): Observable<AmenityModel[]> {
    return this.http.get<AmenityModel[]>(this.apiUrl);
  }

  getAmenityById(id: number): Observable<AmenityModel> {
    return this.http.get<AmenityModel>(`${this.apiUrl}/${id}`);
  }

  addAmenity(data: AmenityModel): Observable<AmenityModel> {
    return this.http.post<AmenityModel>(this.apiUrl, data);
  }

  updateAmenity(id: number, data: AmenityModel): Observable<AmenityModel> {
    return this.http.put<AmenityModel>(`${this.apiUrl}/${id}`, data);
  }

  deleteAmenity(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }

  assignAmenityToRoom(roomId: number, amenityId: number): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/rooms/${roomId}/amenities/${amenityId}`,
      {},
      { responseType: 'text' }
    );
  }

  removeAmenityFromRoom(roomId: number, amenityId: number): Observable<string> {
    return this.http.delete(
      `${this.apiUrl}/rooms/${roomId}/amenities/${amenityId}`,
      { responseType: 'text' }
    );
  }

  getAmenitiesByRoom(roomId: number): Observable<AmenityModel[]> {
    return this.http.get<AmenityModel[]>(`${this.apiUrl}/rooms/${roomId}/amenities`);
  }
}
  

