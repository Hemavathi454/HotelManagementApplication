package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReviewRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReviewRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.ReviewServiceImp;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

	
	

	    @Mock
	    private ReviewRepository reviewRepository;

	    @Mock
	    private ReservationRepository reservationRepository;

	    @InjectMocks
	    private ReviewServiceImp reviewService;

	    // ✅ ADD REVIEW
	   

	    // ❌ RESERVATION NOT FOUND
	    @Test
	    void shouldThrowExceptionWhenReservationNotFound() {

	        ReviewRequestDTO dto = new ReviewRequestDTO();
	        dto.setReservationId(99L);

	        when(reservationRepository.findById(99L))
	                .thenReturn(Optional.empty());

	        assertThrows(RuntimeException.class, () -> {
	            reviewService.addReview(dto);
	        });
	    }

	    // ❌ INVALID RATING
	    @Test
	    void shouldThrowExceptionForInvalidRating() {

	        ReviewRequestDTO dto = new ReviewRequestDTO();
	        dto.setRating(0);

	        assertThrows(RuntimeException.class, () -> {
	            reviewService.addReview(dto);
	        });
	    }

	    // ✅ GET ALL
	    @Test
	    void shouldReturnAllReviews() {

	        when(reviewRepository.findAll())
	                .thenReturn(List.of(new Review(), new Review()));

	        List<ReviewResponseDTO> result = reviewService.getAllReviews();

	        assertEquals(2, result.size());
	    }

	    // ❌ GET BY INVALID ID
	    @Test
	    void shouldThrowExceptionWhenReviewNotFound() {

	        when(reviewRepository.findById(99L))
	                .thenReturn(Optional.empty());

	        assertThrows(RuntimeException.class, () -> {
	            reviewService.getReviewById(99L);
	        });
	    }

	    // ✅ DELETE
	  
	}
