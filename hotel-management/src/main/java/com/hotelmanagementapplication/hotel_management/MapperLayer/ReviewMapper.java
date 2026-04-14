package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;

@Component
public class ReviewMapper {

    public static ReviewDTO toDTO(Review r) {

        if (r == null) return null;

        ReviewDTO dto = new ReviewDTO();
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

    public static Review toEntity(ReviewDTO dto, Reservation reservation, Users user, Hotel hotel) {

        Review r = new Review();

        r.setId(dto.getId());
        r.setRating(dto.getRating());
        r.setComment(dto.getComment());
        r.setReviewDate(dto.getReviewDate());

        r.setReservation(reservation);
        r.setUser(user);
        r.setHotel(hotel);

        return r;
    }

}
