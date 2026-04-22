import { Component } from '@angular/core';
import { AmenityModel } from '../../../shared/models/amenity.model';
import { RoomModel } from '../../../shared/models/room.model';
import { Amenity } from '../../../shared/services/amenity';
import { Room } from '../../../shared/services/room';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-amenities',
  imports: [CommonModule,FormsModule],
  templateUrl: './amenities.html',
  styleUrl: './amenities.css',
})
export class Amenities {
  amenities: AmenityModel[] = [];
  rooms: RoomModel[] = [];
  roomAmenities: AmenityModel[] = [];

  amenityForm: AmenityModel = {
    name: '',
    description: ''
  };

  editingAmenityId: number | null = null;

  selectedRoomId = 0;
  selectedAmenityId = 0;
  searchRoomId = 0;

  message = '';
  errorMessage = '';

  constructor(
    private amenityService: Amenity,
    private roomService: Room
  ) {}

  ngOnInit(): void {
    this.loadAmenities();
    this.loadRooms();
  }

  loadAmenities(): void {
    this.amenityService.getAllAmenities().subscribe({
      next: (data) => {
        this.amenities = data;
      },
      error: (err) => {
        console.error('Error loading amenities:', err);
        this.errorMessage = 'Failed to load amenities';
      }
    });
  }

  loadRooms(): void {
    this.roomService.getAllRooms().subscribe({
      next: (data) => {
        this.rooms = data;
      },
      error: (err) => {
        console.error('Error loading rooms:', err);
      }
    });
  }

  saveAmenity(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.editingAmenityId !== null) {
      this.amenityService.updateAmenity(this.editingAmenityId, this.amenityForm).subscribe({
        next: () => {
          this.message = 'Amenity updated successfully';
          this.resetForm();
          this.loadAmenities();
        },
        error: (err) => {
          console.error('Error updating amenity:', err);
          this.errorMessage = err?.error?.message || err?.error || 'Failed to update amenity';
        }
      });
    } else {
      this.amenityService.addAmenity(this.amenityForm).subscribe({
        next: () => {
          this.message = 'Amenity added successfully';
          this.resetForm();
          this.loadAmenities();
        },
        error: (err) => {
          console.error('Error adding amenity:', err);
          this.errorMessage = err?.error?.message || err?.error || 'Failed to add amenity';
        }
      });
    }
  }

  editAmenity(amenity: AmenityModel): void {
    this.editingAmenityId = amenity.id ?? null;
    this.amenityForm = {
      id: amenity.id,
      name: amenity.name,
      description: amenity.description
    };
  }

  deleteAmenity(id: number): void {
    this.message = '';
    this.errorMessage = '';

    this.amenityService.deleteAmenity(id).subscribe({
      next: () => {
        this.message = 'Amenity deleted successfully';
        this.loadAmenities();
      },
      error: (err) => {
        console.error('Error deleting amenity:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to delete amenity';
      }
    });
  }

  assignAmenityToRoom(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.selectedRoomId || !this.selectedAmenityId) {
      this.errorMessage = 'Please select both room and amenity';
      return;
    }

    this.amenityService.assignAmenityToRoom(this.selectedRoomId, this.selectedAmenityId).subscribe({
      next: (res) => {
        this.message = res;
      },
      error: (err) => {
        console.error('Error assigning amenity:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to assign amenity';
      }
    });
  }

  removeAmenityFromRoom(): void {
    this.message = '';
    this.errorMessage = '';

    if (!this.selectedRoomId || !this.selectedAmenityId) {
      this.errorMessage = 'Please select both room and amenity';
      return;
    }

    this.amenityService.removeAmenityFromRoom(this.selectedRoomId, this.selectedAmenityId).subscribe({
      next: (res) => {
        this.message = res;
      },
      error: (err) => {
        console.error('Error removing amenity:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to remove amenity';
      }
    });
  }

  loadAmenitiesByRoom(): void {
    this.message = '';
    this.errorMessage = '';
    this.roomAmenities = [];

    if (!this.searchRoomId) {
      this.errorMessage = 'Please enter or select a room';
      return;
    }

    this.amenityService.getAmenitiesByRoom(this.searchRoomId).subscribe({
      next: (data) => {
        this.roomAmenities = data;
      },
      error: (err) => {
        console.error('Error loading room amenities:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to load room amenities';
      }
    });
  }

  resetForm(): void {
    this.amenityForm = {
      name: '',
      description: ''
    };
    this.editingAmenityId = null;
  }

  getRoomLabel(room: RoomModel): string {
    return `Room ${room.roomNumber}`;
  }


}
