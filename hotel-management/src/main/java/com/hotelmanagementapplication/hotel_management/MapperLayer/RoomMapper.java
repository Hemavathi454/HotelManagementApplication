package com.hotelmanagementapplication.hotel_management.MapperLayer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;

@Component
public class RoomMapper {
    public static RoomDTO toDTO(Room room) {

        if (room == null) return null;

        RoomDTO dto = new RoomDTO();
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

    public static Room toEntity(RoomDTO dto, Hotel hotel, RoomType roomType, List<Amenity> amenities) {

        Room room = new Room();

        room.setId(dto.getId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setIsAvailable(dto.getIsAvailable());

        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setAmenities(amenities);

        return room;
    }

}
