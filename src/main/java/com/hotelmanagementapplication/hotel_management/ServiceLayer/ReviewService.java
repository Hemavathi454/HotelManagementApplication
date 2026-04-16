package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReviewRequestDTO;

public interface ReviewService {
	ReviewResponseDTO addReview(ReviewRequestDTO dto);

    ReviewResponseDTO getReviewById(Long id);

    List<ReviewResponseDTO> getAllReviews();

    List<ReviewResponseDTO> getReviewsByHotel(Long hotelId);

    ReviewResponseDTO getReviewByReservation(Long reservationId);

    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO dto);

    void deleteReview(Long id);

}
