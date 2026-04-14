package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;

public interface ReservationService {
	 ReservationDTO createReservation(ReservationDTO dto);

	    ReservationDTO getReservationById(Long id);

	    List<ReservationDTO> getAllReservations();

	    List<ReservationDTO> getReservationsByUser(Long userId);

	    ReservationDTO updateReservation(Long id, ReservationDTO dto);

	    void deleteReservation(Long id);

}
