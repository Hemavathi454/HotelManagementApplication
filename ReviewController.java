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

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    // ADD REVIEW
    @PostMapping
    public ReviewDTO addReview(@RequestBody ReviewDTO dto) {
        return reviewService.addReview(dto);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ReviewDTO get(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // GET ALL
    @GetMapping
    public List<ReviewDTO> getAll() {
        return reviewService.getAllReviews();
    }

    // GET BY HOTEL
    @GetMapping("/hotel/{hotelId}")
    public List<ReviewDTO> getByHotel(@PathVariable Long hotelId) {
        return reviewService.getReviewsByHotel(hotelId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ReviewDTO update(@PathVariable Long id,
                            @RequestBody ReviewDTO dto) {
        return reviewService.updateReview(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review deleted successfully";
    }
}
