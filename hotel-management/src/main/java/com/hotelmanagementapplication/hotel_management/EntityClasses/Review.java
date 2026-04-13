package com.hotelmanagementapplication.hotel_management.EntityClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;

	    public int rating;

	    public String comment;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    public Users user;

	    @ManyToOne
	    @JoinColumn(name = "hotel_id")
	    public Hotel hotel;

}
