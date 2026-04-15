package com.hotelmanagementapplication.hotel_management.RepositoryLayer;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.*;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	 List<Hotel> findByLocation(String location);

	    List<Hotel> findByNameContainingIgnoreCase(String name);
}
