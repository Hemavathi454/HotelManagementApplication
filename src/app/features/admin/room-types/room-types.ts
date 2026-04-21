import { Component } from '@angular/core';
import { RoomTypeModel } from '../../../shared/models/room-type.model';
import { RoomType } from '../../../shared/services/room-type';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-room-types',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './room-types.html',
  styleUrl: './room-types.css',
})
export class RoomTypes {
  roomTypes: RoomTypeModel[] = [];

  roomTypeForm: RoomTypeModel = {
    typeName: '',
    description: '',
    maxOccupancy: 1,
    pricePerNight: 0
  };

  editingRoomTypeId: number | null = null;
  message = '';
  errorMessage = '';

  constructor(private roomTypeService: RoomType) {}

  ngOnInit(): void {
    this.loadRoomTypes();
  }

  loadRoomTypes(): void {
    this.roomTypeService.getAllRoomTypes().subscribe({
      next: (data) => {
        this.roomTypes = data;
      },
      error: (err) => {
        console.error('Error loading room types:', err);
        this.errorMessage = 'Failed to load room types';
      }
    });
  }

  saveRoomType(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.editingRoomTypeId !== null) {
      this.roomTypeService.updateRoomType(this.editingRoomTypeId, this.roomTypeForm).subscribe({
        next: () => {
          this.message = 'Room type updated successfully';
          this.resetForm();
          this.loadRoomTypes();
        },
        error: (err) => {
          console.error('Error updating room type:', err);
          this.errorMessage = err?.error?.message || err?.error || 'Failed to update room type';
        }
      });
    } else {
      this.roomTypeService.addRoomType(this.roomTypeForm).subscribe({
        next: () => {
          this.message = 'Room type added successfully';
          this.resetForm();
          this.loadRoomTypes();
        },
        error: (err) => {
          console.error('Error adding room type:', err);
          this.errorMessage = err?.error?.message || err?.error || 'Failed to add room type';
        }
      });
    }
  }

  editRoomType(roomType: RoomTypeModel): void {
    this.editingRoomTypeId = roomType.id ?? null;
    this.roomTypeForm = {
      id: roomType.id,
      typeName: roomType.typeName,
      description: roomType.description,
      maxOccupancy: roomType.maxOccupancy,
      pricePerNight: roomType.pricePerNight
    };
  }

  deleteRoomType(id: number): void {
    this.message = '';
    this.errorMessage = '';

    this.roomTypeService.deleteRoomType(id).subscribe({
      next: () => {
        this.message = 'Room type deleted successfully';
        this.loadRoomTypes();
      },
      error: (err) => {
        console.error('Error deleting room type:', err);
        this.errorMessage = err?.error?.message || err?.error || 'Failed to delete room type';
      }
    });
  }

  resetForm(): void {
    this.roomTypeForm = {
      typeName: '',
      description: '',
      maxOccupancy: 1,
      pricePerNight: 0
    };
    this.editingRoomTypeId = null;
  }

}
