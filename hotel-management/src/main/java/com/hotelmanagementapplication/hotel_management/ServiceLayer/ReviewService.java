package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewDTO;

public interface ReviewService {
	ReviewDTO addReview(ReviewDTO dto);

    ReviewDTO getReviewById(Long id);

    List<ReviewDTO> getAllReviews();

    List<ReviewDTO> getReviewsByHotel(Long hotelId);

    ReviewDTO updateReview(Long id, ReviewDTO dto);

    void deleteReview(Long id);

}

