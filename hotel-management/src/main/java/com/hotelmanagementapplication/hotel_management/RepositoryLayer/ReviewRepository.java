package com.hotelmanagementapplication.hotel_management.RepositoryLayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByHotelId(Long hotelId);

    List<Review> findByUserId(Long userId);

}
