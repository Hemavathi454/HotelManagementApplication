package com.hotelmanagementapplication.hotel_management.EntityClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;

	    public Double amount;

	    public String paymentMethod;

	    public String paymentStatus;

	    @OneToOne
	    @JoinColumn(name = "reservation_id")
	    public Reservation reservation;

}
