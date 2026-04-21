import { Component } from '@angular/core';
import { RoomModel } from '../../../shared/models/room.model';
import { Hotel } from '../../../shared/services/hotel';
import { Room } from './../../../shared/services/room';
import { HotelModel } from '../../../shared/models/hotel.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Amenity } from '../../../shared/services/amenity';
import { AmenityModel } from '../../../shared/models/amenity.model';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomTypeModel } from '../../../shared/models/room-type.model';
import { RoomType } from '../../../shared/services/room-type';
@Component({
  selector: 'app-room-list',
  imports: [CommonModule,FormsModule],
  standalone: true,
templateUrl: './room-list.html',
  styleUrl: './room-list.css',
})
export class RoomList {
  rooms: RoomModel[] = [];
  hotels: HotelModel[] = [];
  roomTypes: RoomTypeModel[] = [];
  amenitiesMap: { [roomId: number]: AmenityModel[] } = {};

  selectedHotelId: number | null = null;
  availableOnly = true;

  errorMessage = '';

  constructor(
    private roomService: Room,
    private hotelService: Hotel,
    private amenityService: Amenity,
    private roomTypeService: RoomType,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadHotels();
    this.loadRoomTypes();

    this.route.queryParams.subscribe(params => {
      const hotelId = Number(params['hotelId']);
      if (hotelId) {
        this.selectedHotelId = hotelId;
      }
      this.loadRooms();
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

  loadRooms(): void {
    this.errorMessage = '';

    if (this.selectedHotelId) {
      this.roomService.getRoomsByHotel(this.selectedHotelId).subscribe({
        next: (data) => {
          this.rooms = this.availableOnly
            ? data.filter(room => room.isAvailable)
            : data;
          this.loadAmenitiesForRooms();
        },
        error: (err) => {
          console.error('Error loading rooms by hotel:', err);
          this.errorMessage = 'Failed to load rooms';
        }
      });
      return;
    }

    if (this.availableOnly) {
      this.roomService.getRoomsByAvailability(true).subscribe({
        next: (data) => {
          this.rooms = data;
          this.loadAmenitiesForRooms();
        },
        error: (err) => {
          console.error('Error loading available rooms:', err);
          this.errorMessage = 'Failed to load rooms';
        }
      });
      return;
    }

    this.roomService.getAllRooms().subscribe({
      next: (data) => {
        this.rooms = data;
        this.loadAmenitiesForRooms();
      },
      error: (err) => {
        console.error('Error loading rooms:', err);
        this.errorMessage = 'Failed to load rooms';
      }
    });
  }

  loadAmenitiesForRooms(): void {
    this.amenitiesMap = {};

    this.rooms.forEach((room) => {
      if (room.id != null) {
        this.amenityService.getAmenitiesByRoom(room.id).subscribe({
          next: (data) => {
            this.amenitiesMap[room.id!] = data;
          },
          error: () => {
            this.amenitiesMap[room.id!] = [];
          }
        });
      }
    });
  }

  applyFilters(): void {
    this.loadRooms();
  }

  clearFilters(): void {
    this.selectedHotelId = null;
    this.availableOnly = true;
    this.loadRooms();
  }

  getHotelName(hotelId: number): string {
    const hotel = this.hotels.find(h => h.id === hotelId);
    return hotel ? hotel.name : 'Unknown Hotel';
  }

  getAmenityNames(roomId: number): string {
    const amenities = this.amenitiesMap[roomId] || [];
    return amenities.length > 0
      ? amenities.map(a => a.name).join(', ')
      : 'No amenities';
  }

  getRoomTypeName(roomTypeId: number): string {
    const roomType = this.roomTypes.find(rt => rt.id === roomTypeId);
    return roomType ? roomType.typeName : 'Unknown Type';
  }

  getPricePerNight(roomTypeId: number): number {
    const roomType = this.roomTypes.find(rt => rt.id === roomTypeId);
    return roomType ? roomType.pricePerNight : 0;
  }

  reserveRoom(room: RoomModel): void {
    this.router.navigate(['/user/booking'], {
      queryParams: {
        hotelId: room.hotelId,
        roomId: room.id
      }
    });
  }

}
