package com.hotelmanagementapplication.hotel_management.DTOLayer;

import java.time.LocalDate;

public class ReviewResponseDTO {
	 private Long id;

	    private Integer rating;
	    private String comment;
	    private LocalDate reviewDate;

	    private Long reservationId;
	    private Long userId;

	    public Long getId() {
	        return id;
	    }

	    public Integer getRating() {
	        return rating;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public LocalDate getReviewDate() {
	        return reviewDate;
	    }

	    public Long getReservationId() {
	        return reservationId;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public void setRating(Integer rating) {
	        this.rating = rating;
	    }

	    public void setComment(String comment) {
	        this.comment = comment;
	    }

	    public void setReviewDate(LocalDate reviewDate) {
	        this.reviewDate = reviewDate;
	    }

	    public void setReservationId(Long reservationId) {
	        this.reservationId = reservationId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

}
