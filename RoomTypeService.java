package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomTypeRequestDTO;

public interface RoomTypeService {
	RoomTypeResponseDTO addRoomType(RoomTypeRequestDTO dto);

    RoomTypeResponseDTO getRoomTypeById(Long id);

    List<RoomTypeResponseDTO> getAllRoomTypes();

    RoomTypeResponseDTO updateRoomType(Long id, RoomTypeRequestDTO dto);

    void deleteRoomType(Long id);
}
