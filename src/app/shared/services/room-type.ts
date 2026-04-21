import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RoomTypeModel } from '../models/room-type.model';

@Injectable({
  providedIn: 'root',
})
export class RoomType {
  private apiUrl = 'http://localhost:8080/room-types';

  constructor(private http: HttpClient) {}

  addRoomType(data: RoomTypeModel): Observable<RoomTypeModel> {
    return this.http.post<RoomTypeModel>(this.apiUrl, data);
  }

  getAllRoomTypes(): Observable<RoomTypeModel[]> {
    return this.http.get<RoomTypeModel[]>(this.apiUrl);
  }

  getRoomTypeById(roomTypeId: number): Observable<RoomTypeModel> {
    return this.http.get<RoomTypeModel>(`${this.apiUrl}/${roomTypeId}`);
  }

  updateRoomType(roomTypeId: number, data: RoomTypeModel): Observable<RoomTypeModel> {
    return this.http.put<RoomTypeModel>(`${this.apiUrl}/${roomTypeId}`, data);
  }

  deleteRoomType(roomTypeId: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${roomTypeId}`, { responseType: 'text' });
  }
  
}
