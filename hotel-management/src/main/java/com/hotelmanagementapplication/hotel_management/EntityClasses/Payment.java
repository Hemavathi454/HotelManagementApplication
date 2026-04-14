package com.hotelmanagementapplication.hotel_management.EntityClasses;

import java.time.LocalDate;

import com.hotelmanagementapplication.hotel_management.enumsLayer.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "payment_id")
	    private Long id;

	    private Double amount;

	    @Column(name = "payment_date")
	    private LocalDate paymentDate;

	   
	    @Enumerated(EnumType.STRING)
	    @Column(name = "payment_status")
	    private PaymentStatus paymentStatus;
	    @OneToOne
	    @JoinColumn(name = "reservation_id")
	    private Reservation reservation;
	

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

		

		public PaymentStatus getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(PaymentStatus paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public Reservation getReservation() {
			return reservation;
		}

		public void setReservation(Reservation reservation) {
			this.reservation = reservation;
		}

		

	

}
