package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomDTO;


public interface RoomService {
	RoomDTO addRoom(RoomDTO roomDTO);

    RoomDTO getRoomById(Long id);

    List<RoomDTO> getAllRooms();

    List<RoomDTO> getRoomsByHotel(Long hotelId);

    RoomDTO updateRoom(Long id, RoomDTO roomDTO);

    void deleteRoom(Long id);
}

