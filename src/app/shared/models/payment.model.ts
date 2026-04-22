export interface PaymentModel {
  id?: number;
  amount: number;
  paymentDate: string;
  paymentStatus: string;
  reservationId: number;
}