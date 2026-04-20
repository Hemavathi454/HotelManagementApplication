package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewResponseDTO;

public interface ReviewService {
	ReviewResponseDTO addReview(ReviewResponseDTO dto);

    ReviewResponseDTO getReviewById(Long id);

    List<ReviewResponseDTO> getAllReviews();

    List<ReviewResponseDTO> getReviewsByHotel(Long hotelId);

    ReviewResponseDTO updateReview(Long id, ReviewResponseDTO dto);

    void deleteReview(Long id);

}

