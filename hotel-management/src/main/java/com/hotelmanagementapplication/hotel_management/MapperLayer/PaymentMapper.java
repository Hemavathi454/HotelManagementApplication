package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.PaymentDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Payment;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.enumsLayer.PaymentStatus;

@Component
public class PaymentMapper {
	public static PaymentDTO toDTO(Payment p) {

        if (p == null) return null;

        PaymentDTO dto = new PaymentDTO();
        dto.setId(p.getId());
        dto.setAmount(p.getAmount());
        dto.setPaymentDate(p.getPaymentDate());

        if (p.getPaymentStatus() != null)
            dto.setPaymentStatus(p.getPaymentStatus().name());

        if (p.getReservation() != null)
            dto.setReservationId(p.getReservation().getId());

        return dto;
    }

    public static Payment toEntity(PaymentDTO dto, Reservation reservation) {

        Payment p = new Payment();

        p.setId(dto.getId());
        p.setAmount(dto.getAmount());
        p.setPaymentDate(dto.getPaymentDate());

        if (dto.getPaymentStatus() != null)
            p.setPaymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus()));

        p.setReservation(reservation);

        return p;
    }

}
