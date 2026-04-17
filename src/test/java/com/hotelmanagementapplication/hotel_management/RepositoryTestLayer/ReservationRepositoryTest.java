package com.hotelmanagementapplication.hotel_management.RepositoryTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReservationService;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

   
    @Test
    void shouldReturnEmptyWhenReservationNotFound() {

        Optional<Reservation> result = reservationRepository.findById(999L);

        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnZeroCount() {

        long count = reservationRepository.count();

        assertEquals(0, count);
    }
    @Test
    void shouldLoadRepository() {
        assertNotNull(reservationRepository);
    }
    @Test
    void shouldReturnEmptyForInvalidId() {

        Optional<Reservation> result =
                reservationRepository.findById(999L);

        assertTrue(result.isEmpty());
    }
}