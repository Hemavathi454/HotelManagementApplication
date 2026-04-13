package com.hotelmanagementapplication.hotel_management.EntityClasses;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Room {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;

	    public String roomNumber;

	    public Double price;

	    public Boolean available;

	    @ManyToOne
	    @JoinColumn(name = "hotel_id")
	    public Hotel hotel;

	    @ManyToOne
	    @JoinColumn(name = "room_type_id")
	    public RoomType roomType;

	    @OneToMany(mappedBy = "room")
	    public List<Reservation> reservations;
}
