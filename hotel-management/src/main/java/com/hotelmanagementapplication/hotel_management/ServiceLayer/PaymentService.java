package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.*;
import com.hotelmanagementapplication.hotel_management.HotelManagementApplication;
import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentDTO;

public interface PaymentService {
	 PaymentDTO createPayment(PaymentDTO dto);

	    PaymentDTO getPaymentById(Long id);

	    List<PaymentDTO> getAllPayments();

	    PaymentDTO updatePayment(Long id, PaymentDTO dto);

	    void deletePayment(Long id);
}
