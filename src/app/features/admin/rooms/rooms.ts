import { Component } from '@angular/core';
import { RoomModel } from '../../../shared/models/room.model';
import { HotelModel } from '../../../shared/models/hotel.model';
import { RoomTypeModel } from '../../../shared/models/room-type.model';
import { Room } from '../../../shared/services/room';
import { Hotel } from '../../../shared/services/hotel';
import { RoomType } from '../../../shared/services/room-type';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-rooms',
  imports: [CommonModule,FormsModule],
  standalone: true, 
  templateUrl: './rooms.html',
  styleUrl: './rooms.css',
})
export class Rooms {
    rooms: RoomModel[] = [];
  hotels: HotelModel[] = [];
  roomTypes: RoomTypeModel[] = [];

  roomForm: RoomModel  = {
    roomNumber: 0,
    isAvailable: true,
    hotelId: 0,
    roomTypeId: 0,
    amenityIds: []
  };

  editingRoomId: number | null = null;
  message = '';
  errorMessage = '';

  constructor(
    private roomService: Room,
    private hotelService: Hotel,
    private roomTypeService: RoomType
  ) {}

  ngOnInit(): void {
    this.loadRooms();
    this.loadHotels();
    this.loadRoomTypes();
  }

  loadRooms(): void {
    this.roomService.getAllRooms().subscribe({
      next: (data) => {
        this.rooms = data;
      },
      error: (err) => {
        console.error('Error loading rooms:', err);
        this.errorMessage = 'Failed to load rooms';
      }
    });
  }

  loadHotels(): void {
    this.hotelService.getAllHotels().subscribe({
      next: (data) => {
        this.hotels = data;
      },
      error: (err) => {
        console.error('Error loading hotels:', err);
      }
    });
  }

  loadRoomTypes(): void {
    this.roomTypeService.getAllRoomTypes().subscribe({
      next: (data) => {
        this.roomTypes = data;
      },
      error: (err) => {
        console.error('Error loading room types:', err);
      }
    });
  }

  saveRoom(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.editingRoomId !== null) {
      this.roomService.updateRoom(this.editingRoomId, this.roomForm).subscribe({
        next: () => {
          this.message = 'Room updated successfully';
          this.resetForm();
          this.loadRooms();
        },
        error: (err) => {
          console.error('Error updating room:', err);
          this.errorMessage = 'Failed to update room';
        }
      });
    } else {
      this.roomService.addRoom(this.roomForm).subscribe({
        next: () => {
          this.message = 'Room added successfully';
          this.resetForm();
          this.loadRooms();
        },
        error: (err) => {
          console.error('Error adding room:', err);
          this.errorMessage = 'Failed to add room';
        }
      });
    }
  }

  editRoom(room: RoomModel): void {
    this.editingRoomId = room.id ?? null;
    this.roomForm = {
      id: room.id,
      roomNumber: room.roomNumber,
      isAvailable: room.isAvailable,
      hotelId: room.hotelId,
      roomTypeId: room.roomTypeId,
      amenityIds: room.amenityIds ?? []
    };
  }

  deleteRoom(roomId: number): void {
    this.message = '';
    this.errorMessage = '';

    this.roomService.deleteRoom(roomId).subscribe({
      next: () => {
        this.message = 'Room deleted successfully';
        this.loadRooms();
      },
      error: (err) => {
        console.error('Error deleting room:', err);
        this.errorMessage = 'Failed to delete room';
      }
    });
  }

  toggleAvailability(room: RoomModel): void {
    if (room.id == null) return;

    this.roomService.updateRoomAvailability(room.id, !room.isAvailable).subscribe({
      next: () => {
        this.message = 'Room availability updated';
        this.loadRooms();
      },
      error: (err) => {
        console.error('Error updating availability:', err);
        this.errorMessage = 'Failed to update room availability';
      }
    });
  }

  resetForm(): void {
    this.roomForm = {
      roomNumber: 0,
      isAvailable: true,
      hotelId: 0,
      roomTypeId: 0,
      amenityIds: []
    };
    this.editingRoomId = null;
  }

  getHotelName(hotelId: number): string {
    const hotel = this.hotels.find(h => h.id === hotelId);
    return hotel ? hotel.name : 'Unknown Hotel';
  }

  getRoomTypeName(roomTypeId: number): string {
    const roomType = this.roomTypes.find(rt => rt.id === roomTypeId);
    return roomType ? roomType.typeName : 'Unknown Type';
  }


}
