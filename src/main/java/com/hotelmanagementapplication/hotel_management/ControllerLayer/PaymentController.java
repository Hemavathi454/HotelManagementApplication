package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.PaymentRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.PaymentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/payments")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    // CREATE
    @PostMapping
    public PaymentResponseDTO create(@Valid @RequestBody PaymentRequestDTO dto) {
        return paymentService.createPayment(dto);
    }

    // GET ALL
    @GetMapping
    public List<PaymentResponseDTO> getAll() {
        return paymentService.getAllPayments();
    }

    // GET BY ID
    @GetMapping("/{paymentId}")
    public PaymentResponseDTO get(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    // GET BY RESERVATION
    @GetMapping("/reservations/{reservationId}/payments")
    public List<PaymentResponseDTO> getByReservation(@PathVariable Long reservationId) {
        return paymentService.getPaymentsByReservation(reservationId);
    }

    // UPDATE STATUS
    @PatchMapping("/{paymentId}/status")
    public PaymentResponseDTO updateStatus(
            @PathVariable Long paymentId,
            @RequestParam @NotBlank(message = "Status cannot be empty") String status) {

        return paymentService.updatePaymentStatus(paymentId, status);
    }
}
