package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.PaymentRequestDTO;

public interface PaymentService {
	 PaymentResponseDTO createPayment(PaymentRequestDTO dto);

	    List<PaymentResponseDTO> getAllPayments();

	    PaymentResponseDTO getPaymentById(Long id);

	    List<PaymentResponseDTO> getPaymentsByReservation(Long reservationId);

	    PaymentResponseDTO updatePaymentStatus(Long paymentId, String status);
}
