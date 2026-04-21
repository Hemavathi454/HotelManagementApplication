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

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReviewRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    // CREATE
    @PostMapping
    public ReviewResponseDTO addReview(@Valid @RequestBody ReviewRequestDTO dto) {
        return reviewService.addReview(dto);
    }

    // GET ALL
    @GetMapping
    public List<ReviewResponseDTO> getAll() {
        return reviewService.getAllReviews();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ReviewResponseDTO get(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // ✅ CORRECT AS PER REQUIREMENT
    // GET /reservations/{reservationId}/review
    @GetMapping("/reservations/{reservationId}/review")
    public ReviewResponseDTO getByReservation(@PathVariable Long reservationId) {
        return reviewService.getReviewByReservation(reservationId);
    }

    // ✅ CORRECT AS PER REQUIREMENT
    // GET /hotels/{hotelId}/reviews
    @GetMapping("/hotels/{hotelId}/reviews")
    public List<ReviewResponseDTO> getByHotel(@PathVariable Long hotelId) {
        return reviewService.getReviewsByHotel(hotelId);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review deleted successfully";
    }
}
