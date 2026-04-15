package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.*;
import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityDTO;

public interface AmenityService {
	AmenityDTO addAmenity(AmenityDTO dto);

    AmenityDTO getAmenityById(Long id);

    List<AmenityDTO> getAllAmenities();

    AmenityDTO updateAmenity(Long id, AmenityDTO dto);

    void deleteAmenity(Long id);

}

