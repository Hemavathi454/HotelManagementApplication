package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.ReservationNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.PaymentMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.PaymentRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.PaymentRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.PaymentService;
import com.hotelmanagementapplication.hotel_management.enumsLayer.PaymentStatus;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // CREATE
    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {

        Reservation reservation = reservationRepository.findById(dto.getReservationId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());

        try {
            payment.setPaymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus().toUpperCase()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid payment status");
        }

        payment.setReservation(reservation);

        return PaymentMapper.toDTO(paymentRepository.save(payment));
    }

    // GET BY ID
    @Override
    public PaymentResponseDTO getPaymentById(Long id) {

        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return PaymentMapper.toDTO(p);
    }

    // GET ALL
    @Override
    public List<PaymentResponseDTO> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toDTO)
                .toList();
    }

    // GET BY RESERVATION
    @Override
    public List<PaymentResponseDTO> getPaymentsByReservation(Long reservationId) {

        return paymentRepository.findByReservationId(reservationId)
                .stream()
                .map(PaymentMapper::toDTO)
                .toList();
    }

    // UPDATE STATUS
    @Override
    public PaymentResponseDTO updatePaymentStatus(Long paymentId, String status) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        try {
            payment.setPaymentStatus(PaymentStatus.valueOf(status.toUpperCase()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid payment status");
        }

        return PaymentMapper.toDTO(paymentRepository.save(payment));
    }

}
