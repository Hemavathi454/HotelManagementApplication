package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;

public interface ReservationService {
	 ReservationResponseDTO createReservation(ReservationRequestDTO dto);

	    ReservationResponseDTO getReservationById(Long id);

	    List<ReservationResponseDTO> getAllReservations();

	    List<ReservationResponseDTO> getReservationsByUser(Long userId);

	    List<ReservationResponseDTO> getReservationsByRoom(Long roomId);

	    List<ReservationResponseDTO> getReservationsByGuestEmail(String email);

	    ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO dto);

	    void deleteReservation(Long id);

}
