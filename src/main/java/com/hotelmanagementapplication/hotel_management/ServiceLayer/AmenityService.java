package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.AmenityRequestDTO;

public interface AmenityService {
	AmenityResponseDTO addAmenity(AmenityRequestDTO dto);

    AmenityResponseDTO getAmenityById(Long id);

    List<AmenityResponseDTO> getAllAmenities();

    AmenityResponseDTO updateAmenity(Long id, AmenityRequestDTO dto);

    void deleteAmenity(Long id);

    void assignAmenityToRoom(Long roomId, Long amenityId);

    void removeAmenityFromRoom(Long roomId, Long amenityId);

    List<AmenityResponseDTO> getAmenitiesByRoom(Long roomId);
}
