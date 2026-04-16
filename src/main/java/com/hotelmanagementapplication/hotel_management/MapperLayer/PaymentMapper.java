package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.enumsLayer.PaymentStatus;

@Component
public class PaymentMapper {

    public static PaymentResponseDTO toDTO(Payment p) {

        if (p == null) return null;

        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(p.getId());
        dto.setAmount(p.getAmount());
        dto.setPaymentDate(p.getPaymentDate());

        if (p.getPaymentStatus() != null)
            dto.setPaymentStatus(p.getPaymentStatus().name());

        if (p.getReservation() != null)
            dto.setReservationId(p.getReservation().getId());

        return dto;
    }

}
