package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeDTO;



public interface RoomTypeService {
	RoomTypeDTO addRoomType(RoomTypeDTO dto);

    RoomTypeDTO getRoomTypeById(Long id);

    List<RoomTypeDTO> getAllRoomTypes();

    RoomTypeDTO updateRoomType(Long id, RoomTypeDTO dto);

    void deleteRoomType(Long id);

}
