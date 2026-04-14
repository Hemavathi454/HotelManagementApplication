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

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	 @Autowired
	    private ReservationService reservationService;

	    @PostMapping
	    public ReservationDTO create(@RequestBody ReservationDTO dto) {
	        return reservationService.createReservation(dto);
	    }

	    @GetMapping("/{id}")
	    public ReservationDTO get(@PathVariable Long id) {
	        return reservationService.getReservationById(id);
	    }

	    @GetMapping
	    public List<ReservationDTO> getAll() {
	        return reservationService.getAllReservations();
	    }

	    @GetMapping("/user/{userId}")
	    public List<ReservationDTO> getByUser(@PathVariable Long userId) {
	        return reservationService.getReservationsByUser(userId);
	    }

	    @PutMapping("/{id}")
	    public ReservationDTO update(@PathVariable Long id,
	                                 @RequestBody ReservationDTO dto) {
	        return reservationService.updateReservation(id, dto);
	    }

	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        reservationService.deleteReservation(id);
	        return "Reservation deleted successfully";
	    }
}
