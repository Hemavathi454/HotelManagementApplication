export interface ReviewModel
 {
  id?: number;
  rating: number;
  comment: string;
  reviewDate?: string;
  reservationId: number;
  userId: number;
}