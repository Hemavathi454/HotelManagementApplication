package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationDTO;

public interface ReservationService {
	 ReservationDTO createReservation(ReservationDTO dto);

	    ReservationDTO getReservationById(Long id);

	    List<ReservationDTO> getAllReservations();

	    List<ReservationDTO> getReservationsByUser(Long userId);

	    ReservationDTO updateReservation(Long id, ReservationDTO dto);

	    void deleteReservation(Long id);

}
