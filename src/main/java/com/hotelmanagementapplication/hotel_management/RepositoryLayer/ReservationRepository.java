package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	List<Reservation> findByUserId(Long userId);

    List<Reservation> findByRoomId(Long roomId);

    List<Reservation> findByGuestEmailIgnoreCase(String guestEmail);
  
}
