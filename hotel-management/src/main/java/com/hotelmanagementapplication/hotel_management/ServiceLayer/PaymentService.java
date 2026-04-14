package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;

public interface PaymentService {
	 PaymentDTO createPayment(PaymentDTO dto);

	    PaymentDTO getPaymentById(Long id);

	    List<PaymentDTO> getAllPayments();

	    PaymentDTO updatePayment(Long id, PaymentDTO dto);

	    void deletePayment(Long id);
}
