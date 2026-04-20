package com.hotelmanagementapplication.hotel_management.MapperLayer;
import java.util.List;

import org.springframework.stereotype.Component;


import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomRequestDTO;

@Component
public class RoomMapper {

    public static Room toEntity(RoomRequestDTO dto,
                                Hotel hotel,
                                RoomType roomType,
                                List<Amenity> amenities) {

        Room room = new Room();

        room.setRoomNumber(dto.getRoomNumber());
        room.setIsAvailable(dto.getIsAvailable());
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setAmenities(amenities);

        return room;
    }

    public static RoomResponseDTO toDTO(Room room) {

        if (room == null) return null;

        RoomResponseDTO dto = new RoomResponseDTO();

        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setIsAvailable(room.getIsAvailable());

        if (room.getHotel() != null)
            dto.setHotelId(room.getHotel().getId());

        if (room.getRoomType() != null)
            dto.setRoomTypeId(room.getRoomType().getId());

        if (room.getAmenities() != null) {
            dto.setAmenityIds(
                    room.getAmenities()
                            .stream()
                            .map(Amenity::getId)
                            .toList()
            );
        }

        return dto;
    }

}
