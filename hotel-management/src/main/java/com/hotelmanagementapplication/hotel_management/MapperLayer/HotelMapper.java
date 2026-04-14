package com.hotelmanagementapplication.hotel_management.MapperLayer;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityDTO;
import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelDTO;
import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomDTO;
import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
@Component
public class HotelMapper {
	public static HotelDTO toDTO(Hotel hotel) {
		 
		        if (hotel == null) return null;

		        HotelDTO dto = new HotelDTO();

		        dto.setId(hotel.getId());
		        dto.setName(hotel.getName());
		        dto.setLocation(hotel.getLocation());
		        dto.setDescription(hotel.getDescription());

		       
		        if (hotel.getAmenities() != null) {
		            dto.setAmenityIds(
		                    hotel.getAmenities()
		                            .stream()
		                            .map(amenity -> amenity.getId())
		                            .collect(Collectors.toList())
		            );
		        }

		        
		        if (hotel.getRooms() != null) {
		            dto.setRoomIds(
		                    hotel.getRooms()
		                            .stream()
		                            .map(room -> room.getId())
		                            .collect(Collectors.toList())
		            );
		        }

		        return dto;
		    }
}
