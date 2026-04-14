package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;

public interface HotelService {

    // CREATE
    HotelDTO addHotel(HotelDTO hotelDTO);

    // READ
    HotelDTO getHotelById(Long id);

    List<HotelDTO> getAllHotels();

    List<HotelDTO> getHotelsByLocation(String location);

    List<HotelDTO> searchHotelsByName(String name);

    // UPDATE
    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);

    // DELETE
    void deleteHotel(Long id);
}
