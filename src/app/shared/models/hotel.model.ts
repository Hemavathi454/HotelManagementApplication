export interface HotelModel {

  id?: number;
  name: string;
  location: string;
  description: string;
  amenityIds: number[];
  roomIds?: number[];
}
