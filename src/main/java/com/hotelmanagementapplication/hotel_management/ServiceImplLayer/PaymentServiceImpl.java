package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.ReservationNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.PaymentMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.PaymentRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.PaymentService;
import com.hotelmanagementapplication.hotel_management.enumsLayer.PaymentStatus;


@Service
public class PaymentServiceImpl implements PaymentService{
	 @Autowired
	    private PaymentRepository paymentRepository;

	    @Autowired
	    private ReservationRepository reservationRepository;

	    // CREATE PAYMENT
	    @Override
	    public PaymentDTO createPayment(PaymentDTO dto) {

	        Reservation reservation = reservationRepository.findById(dto.getReservationId())
	                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

	        Payment payment = PaymentMapper.toEntity(dto, reservation);

	        // default safety
	        if (payment.getPaymentStatus() == null) {
	            payment.setPaymentStatus(PaymentStatus.PENDING);
	        }

	        return PaymentMapper.toDTO(paymentRepository.save(payment));
	    }

	    // GET BY ID
	    @Override
	    public PaymentDTO getPaymentById(Long id) {

	        Payment p = paymentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Payment not found"));

	        return PaymentMapper.toDTO(p);
	    }

	    // GET ALL
	    @Override
	    public List<PaymentDTO> getAllPayments() {

	        return paymentRepository.findAll()
	                .stream()
	                .map(PaymentMapper::toDTO)
	                .toList();
	    }

	    // UPDATE
	    @Override
	    public PaymentDTO updatePayment(Long id, PaymentDTO dto) {

	        Payment p = paymentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Payment not found"));

	        Reservation reservation = reservationRepository.findById(dto.getReservationId())
	                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

	        p.setAmount(dto.getAmount());
	        p.setPaymentDate(dto.getPaymentDate());
	        p.setPaymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus()));
	        p.setReservation(reservation);

	        return PaymentMapper.toDTO(paymentRepository.save(p));
	    }

	    // DELETE
	    @Override
	    public void deletePayment(Long id) {

	        Payment p = paymentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Payment not found"));

	        paymentRepository.delete(p);
	    }

}

