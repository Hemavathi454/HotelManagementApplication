package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	 List<Room> findByHotelId(Long hotelId);

	    List<Room> findByIsAvailable(Boolean isAvailable);
}
