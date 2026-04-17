package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.PaymentController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.PaymentRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.PaymentService;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    // ✅ CREATE PAYMENT
    @Test
    void shouldCreatePaymentSuccessfully() {

        PaymentRequestDTO request = new PaymentRequestDTO();
        request.setAmount(2500.0);
        request.setReservationId(1L);

        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setId(10L);
        response.setAmount(2500.0);

        when(paymentService.createPayment(any())).thenReturn(response);

        PaymentResponseDTO result = paymentController.create(request);

        assertNotNull(result);
        assertEquals(2500.0, result.getAmount());
    }

    // ❌ CREATE - INVALID RESERVATION
    @Test
    void shouldThrowExceptionWhenReservationNotFound() {

        when(paymentService.createPayment(any()))
                .thenThrow(new RuntimeException("Reservation not found"));

        assertThrows(RuntimeException.class, () -> {
            paymentController.create(new PaymentRequestDTO());
        });
    }

    // ✅ GET BY ID
    @Test
    void shouldGetPaymentById() {

        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setId(5L);

        when(paymentService.getPaymentById(5L)).thenReturn(response);

        PaymentResponseDTO result = paymentController.get(5L);

        assertEquals(5L, result.getId());
    }

    // ❌ GET BY ID - NOT FOUND
    @Test
    void shouldThrowExceptionWhenPaymentNotFound() {

        when(paymentService.getPaymentById(99L))
                .thenThrow(new RuntimeException("Payment not found"));

        assertThrows(RuntimeException.class, () -> {
            paymentController.get(99L);
        });
    }

    // ✅ GET ALL
    @Test
    void shouldReturnAllPayments() {

        List<PaymentResponseDTO> list = List.of(
                createPayment(1L, 1000.0),
                createPayment(2L, 2000.0)
        );

        when(paymentService.getAllPayments()).thenReturn(list);

        List<PaymentResponseDTO> result = paymentController.getAll();

        assertEquals(2, result.size());
    }

    // ❌ EMPTY LIST
    @Test
    void shouldReturnEmptyWhenNoPayments() {

        when(paymentService.getAllPayments()).thenReturn(Collections.emptyList());

        assertTrue(paymentController.getAll().isEmpty());
    }

    // ✅ GET BY RESERVATION
    @Test
    void shouldGetPaymentsByReservation() {

        when(paymentService.getPaymentsByReservation(1L))
                .thenReturn(List.of(createPayment(3L, 1500.0)));

        List<PaymentResponseDTO> result =
                paymentController.getByReservation(1L);

        assertEquals(1, result.size());
    }

    // ❌ INVALID RESERVATION
    @Test
    void shouldReturnEmptyForInvalidReservation() {

        when(paymentService.getPaymentsByReservation(50L))
                .thenReturn(Collections.emptyList());

        assertTrue(paymentController.getByReservation(50L).isEmpty());
    }

    // ✅ UPDATE STATUS
    @Test
    void shouldUpdatePaymentStatus() {

        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setId(7L);
        response.setPaymentStatus("PAID");

        when(paymentService.updatePaymentStatus(7L, "PAID"))
                .thenReturn(response);

        PaymentResponseDTO result =
                paymentController.updateStatus(7L, "PAID");

        assertEquals("PAID", result.getPaymentStatus());
    }

    // ❌ INVALID STATUS
    @Test
    void shouldThrowExceptionForInvalidStatus() {

        when(paymentService.updatePaymentStatus(7L, "WRONG"))
                .thenThrow(new RuntimeException("Invalid status"));

        assertThrows(RuntimeException.class, () -> {
            paymentController.updateStatus(7L, "WRONG");
        });
    }

    // 🔧 HELPER
    private PaymentResponseDTO createPayment(Long id, Double amount) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(id);
        dto.setAmount(amount);
        return dto;
    }
}