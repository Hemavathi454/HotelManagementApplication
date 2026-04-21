export interface ReservationModel {
  id?: number;
  guestName: string;
  guestEmail: string;
  guestPhone: string;
  checkInDate: string;
  checkOutDate: string;
  roomId: number;
  userId: number;
}