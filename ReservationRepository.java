package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	 List<Reservation> findByUserId(Long userId);

	    List<Reservation> findByRoomId(Long roomId);

}
