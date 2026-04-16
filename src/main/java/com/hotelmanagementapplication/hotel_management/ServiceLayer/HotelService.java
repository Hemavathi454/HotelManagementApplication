package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.HotelRequestDTO;

public interface HotelService {

	HotelResponseDTO addHotel(HotelRequestDTO dto);

    HotelResponseDTO getHotelById(Long id);

    List<HotelResponseDTO> getAllHotels();

    List<HotelResponseDTO> getHotelsByLocation(String location);

    List<HotelResponseDTO> searchHotelsByName(String name);

    HotelResponseDTO updateHotel(Long id, HotelRequestDTO dto);

    void deleteHotel(Long id);
}
