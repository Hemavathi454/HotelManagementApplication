package com.hotelmanagementapplication.hotel_management.MapperLayer;

import org.springframework.stereotype.Component;

import com.hotelmanagementapplication.hotel_management.ExceptionHandlerEntity.*;
import com.hotelmanagementapplication.hotel_management.HotelManagementApplication;
import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;

@Component
public class AmenityMapper {

    public static AmenityDTO toDTO(Amenity a) {

        if (a == null) return null;

        AmenityDTO dto = new AmenityDTO();
        dto.setId(a.getId());
        dto.setName(a.getName());
        dto.setDescription(a.getDescription());

        return dto;
    }

    public static Amenity toEntity(AmenityDTO dto) {

        Amenity a = new Amenity();

        a.setId(dto.getId());
        a.setName(dto.getName());
        a.setDescription(dto.getDescription());

        return a;
    }

}
