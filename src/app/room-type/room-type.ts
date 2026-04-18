import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RoomTypeService, RoomType, ReservationRequest } from '../room-type-service';

@Component({
  selector: 'app-room-type',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './room-type.html',
  styleUrl: './room-type.css'
})
export class RoomTypeComponent implements OnInit {

  // ── Normal Variables (no signals) ─────────────────────
  rooms: RoomType[]       = [];
  allRoomTypes: string[]  = [];
  isLoading: boolean      = false;
  hasSearched: boolean    = false;
  errorMessage: string    = '';
  selectedRoom: RoomType | null = null;
  showModal: boolean      = false;
  reserveSuccess: boolean = false;
  reserveLoading: boolean = false;

  // ── Forms ─────────────────────────────────────────────
  searchForm!: FormGroup;
  reserveForm!: FormGroup;

  // ── Dropdown Options ──────────────────────────────────
  occupancyOptions: number[] = [1, 2, 3, 4, 6, 8];
  maxPriceOptions: number[]  = [1000, 2000, 3000, 5000, 7000, 8000];

  // ── Constructor (instead of inject()) ─────────────────
  constructor(
    private roomTypeService: RoomTypeService,
    private fb: FormBuilder
  ) {}

  // ── ngOnInit ──────────────────────────────────────────
  ngOnInit(): void {
    this.searchForm = this.fb.group({
      typeName:     [''],
      maxOccupancy: [''],
      maxPrice:     ['']
    });

    this.reserveForm = this.fb.group({
      guestName:      ['', [Validators.required, Validators.minLength(3)]],
      checkInDate:    ['', Validators.required],
      checkOutDate:   ['', Validators.required],
      numberOfGuests: [1, [Validators.required, Validators.min(1)]]
    });

    this.loadRoomTypeNames();
  }

  // ── Load All Room Type Names for Dropdown ─────────────
  loadRoomTypeNames(): void {
    this.roomTypeService.getAllRoomTypes().subscribe({
      next: (data: RoomType[]) => {
        // Remove duplicate type names and sort
        const uniqueTypes: string[] = [];
        data.forEach((room: RoomType) => {
          if (!uniqueTypes.includes(room.typeName)) {
            uniqueTypes.push(room.typeName);
          }
        });
        uniqueTypes.sort();
        this.allRoomTypes = uniqueTypes;
      },
      error: (err: any) => {
        console.error('Error loading room types:', err);
      }
    });
  }

  // ── Search Rooms ──────────────────────────────────────
  onSearch(): void {
    this.isLoading    = true;
    this.hasSearched  = true;
    this.errorMessage = '';
    this.rooms        = [];

    const filter = this.searchForm.value;

    this.roomTypeService.searchRoomTypes(filter).subscribe({
      next: (data: RoomType[]) => {
        this.rooms     = data;
        this.isLoading = false;
      },
      error: (err: any) => {
        console.error('Search error:', err);
        this.errorMessage = 'Failed to fetch rooms. Please check the server.';
        this.isLoading    = false;
      }
    });
  }

  // ── Reset Search ──────────────────────────────────────
  onReset(): void {
    this.searchForm.reset();
    this.rooms        = [];
    this.hasSearched  = false;
    this.errorMessage = '';
  }

  // ── Open Reserve Modal ────────────────────────────────
  openReserve(room: RoomType): void {
    this.selectedRoom   = room;
    this.reserveSuccess = false;
    this.showModal      = true;
    this.reserveForm.reset({ numberOfGuests: 1 });
  }

  // ── Close Modal ───────────────────────────────────────
  closeModal(): void {
    this.showModal    = false;
    this.selectedRoom = null;
    this.reserveForm.reset({ numberOfGuests: 1 });
  }

  // ── Submit Reservation ────────────────────────────────
  onReserve(): void {
    if (this.reserveForm.invalid || this.selectedRoom === null) {
      return;
    }

    this.reserveLoading = true;

    const payload: ReservationRequest = {
      roomTypeId:     this.selectedRoom.id,
      guestName:      this.reserveForm.value.guestName,
      checkInDate:    this.reserveForm.value.checkInDate,
      checkOutDate:   this.reserveForm.value.checkOutDate,
      numberOfGuests: this.reserveForm.value.numberOfGuests
    };

    this.roomTypeService.reserveRoom(payload).subscribe({
      next: (response: any) => {
        console.log('Reservation success:', response);
        this.reserveLoading = false;
        this.reserveSuccess = true;
        setTimeout(() => {
          this.closeModal();
        }, 2500);
      },
      error: (err: any) => {
        console.error('Reservation error:', err);
        this.reserveLoading = false;
        this.errorMessage   = 'Reservation failed. Please try again.';
      }
    });
  }

  // ── Get Occupancy Label ───────────────────────────────
  getOccupancyLabel(occ: number): string {
    if (occ <= 1) return '👤 Solo';
    if (occ <= 2) return '👫 Couple';
    if (occ <= 4) return '👨‍👩‍👧 Small Family';
    return '👨‍👩‍👧‍👦 Large Group';
  }

  // ── Today's Date for min date in date input ───────────
  get todayDate(): string {
    const today = new Date();
    return today.toISOString().split('T')[0];
  }

  // ── Check if Form Field is Invalid ───────────────────
  isFieldInvalid(form: FormGroup, field: string): boolean {
    const control = form.get(field);
    if (control && control.invalid && control.touched) {
      return true;
    }
    return false;
  }

}