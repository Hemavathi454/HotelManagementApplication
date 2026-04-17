package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    // ✅ CREATE
    @Test
    void shouldCreateReservationSuccessfully() {

        ReservationRequestDTO dto = new ReservationRequestDTO();
        dto.setUserId(1L);
        dto.setRoomId(2L);
        dto.setCheckInDate(LocalDate.now());
        dto.setCheckOutDate(LocalDate.now().plusDays(2));

        Users user = new Users();
        user.setId(1L);

        Room room = new Room();
        room.setId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roomRepository.findById(2L)).thenReturn(Optional.of(room));
        when(reservationRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        ReservationResponseDTO result = reservationService.createReservation(dto);

        assertNotNull(result);
    }

    // ❌ INVALID DATE
    @Test
    void shouldThrowExceptionForInvalidDates() {

        ReservationRequestDTO dto = new ReservationRequestDTO();
        dto.setCheckInDate(LocalDate.now().plusDays(5));
        dto.setCheckOutDate(LocalDate.now());

        assertThrows(RuntimeException.class, () -> {
            reservationService.createReservation(dto);
        });
    }

    // ✅ GET BY ID
    @Test
    void shouldGetReservationById() {

        Reservation res = new Reservation();
        res.setId(1L);

        when(reservationRepository.findById(1L))
                .thenReturn(Optional.of(res));

        ReservationResponseDTO result = reservationService.getReservationById(1L);

        assertEquals(1L, result.getId());
    }

    // ❌ NOT FOUND
    @Test
    void shouldThrowExceptionWhenReservationNotFound() {

        when(reservationRepository.findById(10L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            reservationService.getReservationById(10L);
        });
    }

    // ✅ DELETE
    @Test
    void shouldDeleteReservation() {

        Reservation res = new Reservation();
        res.setId(5L);

        when(reservationRepository.findById(5L))
                .thenReturn(Optional.of(res));

        reservationService.deleteReservation(5L);

        verify(reservationRepository).delete(res);
    }
    
    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        ReservationRequestDTO dto = new ReservationRequestDTO();
        dto.setUserId(99L);
        dto.setRoomId(1L);
        dto.setCheckInDate(LocalDate.now());
        dto.setCheckOutDate(LocalDate.now().plusDays(2));

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // ALSO mock room to avoid NPE chain
        Room room = new Room();
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        room.setHotel(hotel);

        when(roomRepository.findById(anyLong()))
                .thenReturn(Optional.of(room));

        assertThrows(RuntimeException.class, () -> {
            reservationService.createReservation(dto);
        });
       
    }
    
    @Test
    void shouldThrowExceptionWhenSameCheckInAndCheckOut() {

        ReservationRequestDTO dto = new ReservationRequestDTO();
        dto.setCheckInDate(LocalDate.now());
        dto.setCheckOutDate(LocalDate.now());

        assertThrows(RuntimeException.class, () -> {
            reservationService.createReservation(dto);
        });
    }
    @Test
    void shouldThrowExceptionWhenDatesAreNull() {

        ReservationRequestDTO dto = new ReservationRequestDTO();
        dto.setUserId(1L);
        dto.setRoomId(1L);

        assertThrows(RuntimeException.class, () -> {
            reservationService.createReservation(dto);
        });
    }
    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingReservation() {

        when(reservationRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            reservationService.updateReservation(100L, new ReservationRequestDTO());
        });
    }
    @Test
    void shouldThrowExceptionWhenDeletingInvalidReservation() {

        when(reservationRepository.findById(200L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            reservationService.deleteReservation(200L);
        });
    }
}
