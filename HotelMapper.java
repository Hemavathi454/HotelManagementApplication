package com.hotelmanagementapplication.hotel_management.MapperLayer;

import java.util.List;


import org.springframework.stereotype.Component;


import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelResponseDTO;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;

import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.HotelRequestDTO;

@Component
public class HotelMapper {

    // ENTITY → RESPONSE DTO
    public static HotelResponseDTO toDTO(Hotel hotel) {

        if (hotel == null) return null;

        HotelResponseDTO dto = new HotelResponseDTO();

        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setLocation(hotel.getLocation());
        dto.setDescription(hotel.getDescription());

        // Amenities → IDs
        if (hotel.getAmenities() != null) {
            dto.setAmenityIds(
                    hotel.getAmenities()
                            .stream()
                            .map(Amenity::getId)
                            .toList()
            );
        }

        // Rooms → IDs
        if (hotel.getRooms() != null) {
            dto.setRoomIds(
                    hotel.getRooms()
                            .stream()
                            .map(Room::getId)
                            .toList()
            );
        }

        return dto;
    }

    // REQUEST DTO -> ENTITY
    public static Hotel toEntity(HotelRequestDTO dto, List<Amenity> amenities) {

        Hotel hotel = new Hotel();

        // ❌ DO NOT SET ID (important)
        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
        hotel.setDescription(dto.getDescription());

        if (amenities != null) {
            hotel.setAmenities(amenities);
        }

        return hotel;
    }

 
}
