package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.PaymentRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.PaymentRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldThrowExceptionForZeroAmount() {

        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setAmount(0.0);

        assertThrows(RuntimeException.class, () -> {
            paymentService.createPayment(dto);
        });
    }
    @Test
    void shouldThrowExceptionForNullRequest() {

        assertThrows(Exception.class, () -> {
            paymentService.createPayment(null);
        });
    }
    @Test
    void shouldReturnAllPayments() {

        when(paymentRepository.findAll())
                .thenReturn(List.of(new Payment(), new Payment()));

        List<PaymentResponseDTO> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
    }
    @Test
    void shouldThrowExceptionWhenPaymentNotFound() {

        when(paymentRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            paymentService.getPaymentById(99L);
        });
    }
    // ❌ RESERVATION NOT FOUND
    @Test
    void shouldThrowExceptionWhenReservationNotFound() {

        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setReservationId(99L);

        when(reservationRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            paymentService.createPayment(dto);
        });
    }

    

    // ❌ INVALID AMOUNT
    @Test
    void shouldThrowExceptionForInvalidAmount() {

        PaymentRequestDTO dto = new PaymentRequestDTO();
        dto.setAmount(-100.0);

        assertThrows(RuntimeException.class, () -> {
            paymentService.createPayment(dto);
        });
    }

    // ✅ UPDATE STATUS
    @Test
    void shouldUpdatePaymentStatus() {

        Payment payment = new Payment();
        payment.setId(1L);

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any())).thenReturn(payment);

        PaymentResponseDTO result =
                paymentService.updatePaymentStatus(1L, "PAID");

        assertNotNull(result);
    }

    // ❌ INVALID STATUS
    @Test
    void shouldThrowExceptionForInvalidStatus() {

        assertThrows(RuntimeException.class, () -> {
            paymentService.updatePaymentStatus(1L, "INVALID");
        });
    }
}