package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomTypeRequestDTO;

@Component
public class RoomTypeMapper {
	   public static RoomTypeResponseDTO toDTO(RoomType r) {

	        if (r == null) return null;

	        RoomTypeResponseDTO dto = new RoomTypeResponseDTO();
	        dto.setId(r.getId());
	        dto.setTypeName(r.getTypeName());
	        dto.setDescription(r.getDescription());
	        dto.setMaxOccupancy(r.getMaxOccupancy());
	        dto.setPricePerNight(r.getPricePerNight());

	        return dto;
	    }

	    public static RoomType toEntity(RoomTypeRequestDTO dto) {

	        RoomType r = new RoomType();

	        r.setTypeName(dto.getTypeName());
	        r.setDescription(dto.getDescription());
	        r.setMaxOccupancy(dto.getMaxOccupancy());
	        r.setPricePerNight(dto.getPricePerNight());

	        return r;
	    }
}
