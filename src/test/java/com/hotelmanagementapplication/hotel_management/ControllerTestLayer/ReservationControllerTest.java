package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.ReservationController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReservationService;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    // ✅ CREATE - SUCCESS
    @Test
    void shouldCreateReservationSuccessfully() {

        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setUserId(1L);
        request.setRoomId(2L);
        request.setCheckInDate(LocalDate
        		.of(2026, 5, 1));
        request.setCheckOutDate(LocalDate.of(2026, 5, 5));

        ReservationResponseDTO response = new ReservationResponseDTO();
        response.setId(10L);

        when(reservationService.createReservation(any()))
                .thenReturn(response);

        ReservationResponseDTO result = reservationController.create(request);

        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    // ❌ CREATE - ROOM NOT AVAILABLE
    @Test
    void shouldThrowExceptionWhenRoomNotAvailable() {

        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setRoomId(2L);

        when(reservationService.createReservation(any()))
                .thenThrow(new RuntimeException("Room not available"));

        assertThrows(RuntimeException.class, () -> {
            reservationController.create(request);
        });
    }

    // ❌ CREATE - INVALID DATE (check-out before check-in)
    @Test
    void shouldThrowExceptionForInvalidDates() {

        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setCheckInDate(LocalDate.of(2026, 5, 10));
        request.setCheckOutDate(LocalDate.of(2026, 5, 5));

        when(reservationService.createReservation(any()))
                .thenThrow(new RuntimeException("Invalid date range"));

        assertThrows(RuntimeException.class, () -> {
            reservationController.create(request);
        });
    }

    // ✅ GET BY ID
    @Test
    void shouldGetReservationById() {

        ReservationResponseDTO response = new ReservationResponseDTO();
        response.setId(20L);

        when(reservationService.getReservationById(20L))
                .thenReturn(response);

        ReservationResponseDTO result = reservationController.get(20L);

        assertEquals(20L, result.getId());
    }

    // ❌ GET BY ID - NOT FOUND
    @Test
    void shouldThrowExceptionWhenReservationNotFound() {

        when(reservationService.getReservationById(99L))
                .thenThrow(new RuntimeException("Reservation not found"));

        assertThrows(RuntimeException.class, () -> {
            reservationController.get(99L);
        });
    }

    // ✅ GET ALL
    @Test
    void shouldReturnAllReservations() {

        List<ReservationResponseDTO> list = List.of(
                createReservation(1L),
                createReservation(2L)
        );

        when(reservationService.getAllReservations())
                .thenReturn(list);

        List<ReservationResponseDTO> result = reservationController.getAll();

        assertEquals(2, result.size());
    }

    // ❌ GET ALL - EMPTY
    @Test
    void shouldReturnEmptyListWhenNoReservations() {

        when(reservationService.getAllReservations())
                .thenReturn(Collections.emptyList());

        List<ReservationResponseDTO> result = reservationController.getAll();

        assertTrue(result.isEmpty());
    }

    // ✅ GET BY ROOM
    @Test
    void shouldGetReservationsByRoom() {

        List<ReservationResponseDTO> list = List.of(
                createReservation(3L)
        );

        when(reservationService.getReservationsByRoom(5L))
                .thenReturn(list);

        List<ReservationResponseDTO> result =
                reservationController.getByRoom(5L);

        assertEquals(1, result.size());
    }

    // ❌ GET BY ROOM - EMPTY
    @Test
    void shouldReturnEmptyWhenNoReservationsForRoom() {

        when(reservationService.getReservationsByRoom(10L))
                .thenReturn(Collections.emptyList());

        List<ReservationResponseDTO> result =
                reservationController.getByRoom(10L);

        assertTrue(result.isEmpty());
    }

    // ✅ GET BY EMAIL
    @Test
    void shouldGetReservationsByEmail() {

        List<ReservationResponseDTO> list = List.of(
                createReservation(7L)
        );

        when(reservationService.getReservationsByGuestEmail("user@mail.com"))
                .thenReturn(list);

        List<ReservationResponseDTO> result =
                reservationController.getByEmail("user@mail.com");

        assertEquals(1, result.size());
    }

    // ❌ GET BY EMAIL - NOT FOUND
    @Test
    void shouldReturnEmptyForInvalidEmail() {

        when(reservationService.getReservationsByGuestEmail("invalid@mail.com"))
                .thenReturn(Collections.emptyList());

        List<ReservationResponseDTO> result =
                reservationController.getByEmail("invalid@mail.com");

        assertTrue(result.isEmpty());
    }

    // ✅ UPDATE - SUCCESS
   

    // ✅ DELETE - SUCCESS
    @Test
    void shouldDeleteReservationSuccessfully() {

        doNothing().when(reservationService).deleteReservation(40L);

        String result = reservationController.delete(40L);

        assertEquals("Reservation deleted successfully", result);
    }

    // ❌ DELETE - NOT FOUND
    @Test
    void shouldThrowExceptionWhenDeletingInvalidReservation() {

        doThrow(new RuntimeException("Reservation not found"))
                .when(reservationService).deleteReservation(80L);

        assertThrows(RuntimeException.class, () -> {
            reservationController.delete(80L);
        });
    }
    @Test
    void shouldUpdateReservationSuccessfully() {

        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setUserId(2L);
        request.setRoomId(3L);
        request.setCheckInDate(LocalDate.of(2026, 6, 1));
        request.setCheckOutDate(LocalDate.of(2026, 6, 5));

        ReservationResponseDTO response = new ReservationResponseDTO();
        response.setId(30L);

        when(reservationService.updateReservation(eq(30L), any()))
                .thenReturn(response);

        ReservationResponseDTO result =
                reservationController.update(30L, request);

        assertEquals(30L, result.getId());
    }
    @Test
    void shouldThrowExceptionWhenUpdatingInvalidReservation() {

        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setUserId(1L);
        request.setRoomId(1L);
        request.setCheckInDate(LocalDate.now());
        request.setCheckOutDate(LocalDate.now().plusDays(2));

        when(reservationService.updateReservation(eq(50L), any()))
                .thenThrow(new RuntimeException("Reservation not found"));

        assertThrows(RuntimeException.class, () -> {
            reservationController.update(50L, request);
        });
    }

    // 🔧 HELPER
    private ReservationResponseDTO createReservation(Long id) {

        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(id);

        return dto;
    }
}