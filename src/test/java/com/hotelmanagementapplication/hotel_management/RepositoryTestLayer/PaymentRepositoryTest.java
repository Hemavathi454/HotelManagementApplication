package com.hotelmanagementapplication.hotel_management.RepositoryTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Review;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.PaymentRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReviewRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.PaymentRequestDTO;
import com.hotelmanagementapplication.hotel_management.enumsLayer.PaymentStatus;



    @DataJpaTest
    class PaymentRepositoryTest {

        @Autowired
        private PaymentRepository paymentRepository;

        @Autowired
        private ReservationRepository reservationRepository;
        @Autowired
        private ReviewRepository reviewRepository; 

       
        @Test
        void shouldReturnZeroWhenNoPayments() {

            long count = paymentRepository.count();

            assertEquals(0, count);
        }
       
    // ❌ EMPTY CASE
    @Test                             
    void shouldReturnEmptyWhenNoPayments() {

        List<Payment> list = paymentRepository.findAll();

        assertTrue(list.isEmpty());
    }
   
    @Test
    void shouldLoadRepository() {
        assertNotNull(paymentRepository);
    }
    @Test
    void shouldReturnEmptyForInvalidId() {

        Optional<Payment> result =
                paymentRepository.findById(999L);

        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnEmptyInitially() {

        List<Payment> list = paymentRepository.findAll();

        assertTrue(list.isEmpty());
    }

}