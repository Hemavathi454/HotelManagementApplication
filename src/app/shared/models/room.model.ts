export interface RoomModel {
  id?: number;
  roomNumber: number;
  isAvailable: boolean;
  hotelId: number;
  roomTypeId: number;
  amenityIds: number[];
}