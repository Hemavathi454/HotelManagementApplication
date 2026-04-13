package com.hotelmanagementapplication.hotel_management.EntityClasses;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;

	    public LocalDate checkInDate;

	    public LocalDate checkOutDate;

	    public String status;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    public Users user;

	    @ManyToOne
	    @JoinColumn(name = "room_id")
	    public Room room;

	    @OneToOne(mappedBy = "reservation")
	    public Payment payment;

}
