package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;

@Component
public class RoomTypeMapper {
	 public static RoomTypeDTO toDTO(RoomType r) {

	        if (r == null) return null;

	        RoomTypeDTO dto = new RoomTypeDTO();
	        dto.setId(r.getId());
	        dto.setTypeName(r.getTypeName());
	        dto.setDescription(r.getDescription());
	        dto.setMaxOccupancy(r.getMaxOccupancy() != null ? r.getMaxOccupancy() : 0);
	        dto.setPricePerNight(r.getPricePerNight() != null ? r.getPricePerNight() : 0.0);

	        return dto;
	    }

	    public static RoomType toEntity(RoomTypeDTO dto) {

	        RoomType r = new RoomType();

	        r.setId(dto.getId());
	        r.setTypeName(dto.getTypeName());
	        r.setDescription(dto.getDescription());
	        r.setMaxOccupancy(dto.getMaxOccupancy());
	        r.setPricePerNight(dto.getPricePerNight());

	        return r;
	    }

}
