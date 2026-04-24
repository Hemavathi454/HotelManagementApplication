package com.hotelmanagementapplication.hotel_management.DTOLayer;

import java.time.LocalDate;

public class PaymentResponseDTO {
	 private Long id;
	    private Double amount;
	    private LocalDate paymentDate;
	    private String paymentStatus;
	    private Long reservationId;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public LocalDate getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}
		public String getPaymentStatus() {
			return paymentStatus;
		}
		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}
		public Long getReservationId() {
			return reservationId;
		}
		public void setReservationId(Long reservationId) {
			this.reservationId = reservationId;
		}
}