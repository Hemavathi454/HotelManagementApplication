package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReviewResponseDTO;
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
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReviewRequestDTO;
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

    // CREATE
    @Override
    public ReviewResponseDTO addReview(ReviewRequestDTO dto) {

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Hotel hotel = reservation.getRoom().getHotel();

        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setReviewDate(dto.getReviewDate() != null ? dto.getReviewDate() : LocalDate.now());
        review.setReservation(reservation);
        review.setUser(user);
        review.setHotel(hotel);

        return ReviewMapper.toDTO(reviewRepository.save(review));
    }

    // GET BY ID
    @Override
    public ReviewResponseDTO getReviewById(Long id) {

        Review r = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return ReviewMapper.toDTO(r);
    }

    // GET ALL
    @Override
    public List<ReviewResponseDTO> getAllReviews() {

        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    // GET BY HOTEL
    @Override
    public List<ReviewResponseDTO> getReviewsByHotel(Long hotelId) {

        return reviewRepository.findByHotelId(hotelId)
                .stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    // ✅ FIXED METHOD (you had wrong logic earlier)
    @Override
    public ReviewResponseDTO getReviewByReservation(Long reservationId) {

        Review review = reviewRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new RuntimeException("Review not found for reservation"));

        return ReviewMapper.toDTO(review);
    }

    // UPDATE
    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO dto) {

        Review r = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Hotel hotel = reservation.getRoom().getHotel();

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
