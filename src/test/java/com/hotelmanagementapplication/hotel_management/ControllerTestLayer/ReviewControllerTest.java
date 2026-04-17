package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.ReviewController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReviewRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReviewService;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

	
	

	    @Mock
	    private ReviewService reviewService;

	    @InjectMocks
	    private ReviewController reviewController;

	    // ✅ ADD REVIEW
	    @Test
	    void shouldAddReviewSuccessfully() {

	        ReviewRequestDTO req = new ReviewRequestDTO();
	        req.setRating(5);
	        req.setComment("Excellent stay");

	        ReviewResponseDTO res = new ReviewResponseDTO();
	        res.setId(1L);
	        res.setRating(5);

	        when(reviewService.addReview(any())).thenReturn(res);

	        ReviewResponseDTO result = reviewController.addReview(req);

	        assertNotNull(result);
	        assertEquals(5, result.getRating());
	    }

	    // ❌ ADD REVIEW - INVALID
	    @Test
	    void shouldThrowExceptionWhenInvalidReview() {

	        when(reviewService.addReview(any()))
	                .thenThrow(new RuntimeException("Invalid review"));

	        assertThrows(RuntimeException.class, () -> {
	            reviewController.addReview(new ReviewRequestDTO());
	        });
	    }

	    // ✅ GET ALL
	    @Test
	    void shouldGetAllReviews() {

	        when(reviewService.getAllReviews())
	                .thenReturn(List.of(new ReviewResponseDTO(), new ReviewResponseDTO()));

	        List<ReviewResponseDTO> result = reviewController.getAll();

	        assertEquals(2, result.size());
	    }

	    // ❌ EMPTY LIST
	    @Test
	    void shouldReturnEmptyWhenNoReviews() {

	        when(reviewService.getAllReviews())
	                .thenReturn(Collections.emptyList());

	        assertTrue(reviewController.getAll().isEmpty());
	    }

	    // ✅ GET BY ID
	    @Test
	    void shouldGetReviewById() {

	        ReviewResponseDTO res = new ReviewResponseDTO();
	        res.setId(10L);

	        when(reviewService.getReviewById(10L)).thenReturn(res);

	        ReviewResponseDTO result = reviewController.get(10L);

	        assertEquals(10L, result.getId());
	    }

	    // ❌ NOT FOUND
	    @Test
	    void shouldThrowExceptionWhenReviewNotFound() {

	        when(reviewService.getReviewById(99L))
	                .thenThrow(new RuntimeException("Review not found"));

	        assertThrows(RuntimeException.class, () -> {
	            reviewController.get(99L);
	        });
	    }

	    // ✅ GET BY HOTEL
	    @Test
	    void shouldGetReviewsByHotel_Inverted() {

	        when(reviewService.getReviewsByHotel(1L))
	                .thenReturn(List.of(new ReviewResponseDTO()));

	        List<ReviewResponseDTO> result =
	                reviewController.getByHotel(1L);

	        // intentionally wrong
	        assertNotEquals(1, result.size());
	    }
	    @Test
	    void shouldReturnEmptyForInvalidHotel_Inverted() {

	        when(reviewService.getReviewsByHotel(50L))
	                .thenReturn(Collections.emptyList());

	        // wrong expectation
	        assertFalse(reviewController.getByHotel(50L).isEmpty());
	    }
	    @Test
	    void shouldDeleteReview_Inverted() {

	        doNothing().when(reviewService).deleteReview(1L);

	        String result = reviewController.delete(1L);

	        // intentionally wrong
	        assertNotEquals("Review deleted successfully", result);
	    }
	    @Test
	    void shouldThrowExceptionWhenDeletingInvalidReview_Inverted() {

	        doThrow(new RuntimeException("Not found"))
	                .when(reviewService).deleteReview(99L);

	        // wrong expectation
	        assertDoesNotThrow(() -> {
	            reviewController.delete(99L);
	        });
	    }
	}


