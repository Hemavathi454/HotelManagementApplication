package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.AmenityRequestDTO;

@Component
public class AmenityMapper {



    public static AmenityResponseDTO toDTO(Amenity a) {

        if (a == null) return null;

        AmenityResponseDTO dto = new AmenityResponseDTO();
        dto.setId(a.getId());
        dto.setName(a.getName());
        dto.setDescription(a.getDescription());

        return dto;
    }

    public static Amenity toEntity(AmenityRequestDTO dto) {

        Amenity a = new Amenity();

        // ❌ REMOVE THIS (very important)
        // a.setId(dto.getId());

        a.setName(dto.getName());
        a.setDescription(dto.getDescription());

        return a;
    }


}
