package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;

@Component
public class ReservationMapper {

    public static ReservationResponseDTO toDTO(Reservation r) {

        if (r == null) return null;

        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(r.getId());
        dto.setGuestName(r.getGuestName());
        dto.setGuestEmail(r.getGuestEmail());
        dto.setGuestPhone(r.getGuestPhone());
        dto.setCheckInDate(r.getCheckInDate());
        dto.setCheckOutDate(r.getCheckOutDate());

        if (r.getRoom() != null)
            dto.setRoomId(r.getRoom().getId());

        if (r.getUser() != null)
            dto.setUserId(r.getUser().getId());

        return dto;
    }

    public static Reservation toEntity(ReservationRequestDTO dto,
                                       Room room,
                                       Users user) {

        Reservation r = new Reservation();

        r.setGuestName(dto.getGuestName());
        r.setGuestEmail(dto.getGuestEmail());
        r.setGuestPhone(dto.getGuestPhone());
        r.setCheckInDate(dto.getCheckInDate());
        r.setCheckOutDate(dto.getCheckOutDate());
        r.setRoom(room);
        r.setUser(user);

        return r;
    }

}
