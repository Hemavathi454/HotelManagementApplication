package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;

@Component
public class ReviewMapper {
	public static ReviewResponseDTO toDTO(Review r) {
	 if (r == null) return null;

     ReviewResponseDTO dto = new ReviewResponseDTO();
     dto.setId(r.getId());
     dto.setRating(r.getRating());
     dto.setComment(r.getComment());
     dto.setReviewDate(r.getReviewDate());

     if (r.getReservation() != null)
         dto.setReservationId(r.getReservation().getId());

     if (r.getUser() != null)
         dto.setUserId(r.getUser().getId());

     return dto;
 }

}