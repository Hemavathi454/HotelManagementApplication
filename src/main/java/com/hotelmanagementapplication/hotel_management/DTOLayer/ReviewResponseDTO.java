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
		public void setId(Long id) {
			this.id = id;
		}
		public Integer getRating() {
			return rating;
		}
		public void setRating(Integer rating) {
			this.rating = rating;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public LocalDate getReviewDate() {
			return reviewDate;
		}
		public void setReviewDate(LocalDate reviewDate) {
			this.reviewDate = reviewDate;
		}
		public Long getReservationId() {
			return reservationId;
		}
		public void setReservationId(Long reservationId) {
			this.reservationId = reservationId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
}
