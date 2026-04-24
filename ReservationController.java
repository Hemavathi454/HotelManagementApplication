package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationResponseDTO;

import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	   @Autowired
	    private ReservationService reservationService;

	    // CREATE
	    @PostMapping
	    public ReservationResponseDTO create(@Valid @RequestBody ReservationRequestDTO dto) {
	        return reservationService.createReservation(dto);
	    }

	    // GET BY ID
	    @GetMapping("/{reservationId}")
	    public ReservationResponseDTO get(@PathVariable Long reservationId) {
	        return reservationService.getReservationById(reservationId);
	    }

	    // GET ALL
	    @GetMapping
	    public List<ReservationResponseDTO> getAll() {
	        return reservationService.getAllReservations();
	    }

	    // ✅ FIXED ENDPOINT (as per requirement)
	    @GetMapping("/rooms/{roomId}/reservations")
	    public List<ReservationResponseDTO> getByRoom(@PathVariable Long roomId) {
	        return reservationService.getReservationsByRoom(roomId);
	    }

	    // GET BY EMAIL (already correct)
	    @GetMapping("/guest-email/{email}")
	    public List<ReservationResponseDTO> getByEmail(@PathVariable String email) {
	        return reservationService.getReservationsByGuestEmail(email);
	    }

	    // UPDATE
	    @PutMapping("/{reservationId}")
	    public ReservationResponseDTO update(@PathVariable Long reservationId,
	                                         @Valid @RequestBody ReservationRequestDTO dto) {
	        return reservationService.updateReservation(reservationId, dto);
	    }

	    // DELETE
	    @DeleteMapping("/{reservationId}")
	    public String delete(@PathVariable Long reservationId) {
	        reservationService.deleteReservation(reservationId);
	        return "Reservation deleted successfully";
	    }
}
