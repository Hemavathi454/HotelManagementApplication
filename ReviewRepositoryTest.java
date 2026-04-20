package com.hotelmanagementapplication.hotel_management.RepositoryTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReviewRepository;

@DataJpaTest
public class ReviewRepositoryTest {

	@Autowired
	private ReservationRepository reservationRepository;

	    @Autowired
	    private ReviewRepository reviewRepository;

	   
	    @Test
	    void shouldCheckCount_Inverted() {

	        long count = reviewRepository.count();

	        // intentionally wrong
	        assertNotEquals(0, count);
	    }

	    @Test
	    void shouldReturnEmptyWhenNoReviews_Inverted() {

	        List<Review> list = reviewRepository.findAll();

	        // wrong expectation
	        assertFalse(list.isEmpty());
	    }
	    @Test
	    void shouldLoadRepository() {
	        assertNotNull(reviewRepository);
	    }
	    @Test
	    void shouldReturnEmptyForInvalidId() {

	        Optional<Review> result =
	                reviewRepository.findById(999L);

	        assertTrue(result.isEmpty());
	    }

	}

	


