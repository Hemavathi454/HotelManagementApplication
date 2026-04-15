package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.HotelNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.ReservationNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.ReviewMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReviewRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReviewService;

@Service
public class ReviewServiceImp implements ReviewService  {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    // CREATE REVIEW
    @Override
    public ReviewDTO addReview(ReviewDTO dto) {

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Hotel hotel = hotelRepository.findById(reservation.getRoom().getHotel().getId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        Review review = ReviewMapper.toEntity(dto, reservation, user, hotel);

        if (review.getReviewDate() == null) {
            review.setReviewDate(LocalDate.now());
        }

        return ReviewMapper.toDTO(reviewRepository.save(review));
    }

    // GET BY ID
    @Override
    public ReviewDTO getReviewById(Long id) {

        Review r = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return ReviewMapper.toDTO(r);
    }

    // GET ALL
    @Override
    public List<ReviewDTO> getAllReviews() {

        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    // GET BY HOTEL
    @Override
    public List<ReviewDTO> getReviewsByHotel(Long hotelId) {

        return reviewRepository.findByHotelId(hotelId)
                .stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    // UPDATE
    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO dto) {

        Review r = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Hotel hotel = hotelRepository.findById(reservation.getRoom().getHotel().getId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        r.setRating(dto.getRating());
        r.setComment(dto.getComment());
        r.setReviewDate(dto.getReviewDate());
        r.setReservation(reservation);
        r.setUser(user);
        r.setHotel(hotel);

        return ReviewMapper.toDTO(reviewRepository.save(r));
    }

    // DELETE
    @Override
    public void deleteReview(Long id) {

        Review r = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(r);
    }
}
