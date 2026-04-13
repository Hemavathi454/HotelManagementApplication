package com.hotelmanagementapplication.hotel_management.EntityClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Amenity {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;

	    public String name;

	    public String description;

}
