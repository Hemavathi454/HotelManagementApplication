package com.hotelmanagementapplication.hotel_management.ServiceLayer;

import java.util.List;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomRequestDTO;

public interface RoomService {
	 RoomResponseDTO addRoom(RoomRequestDTO dto);

	    RoomResponseDTO getRoomById(Long id);

	    List<RoomResponseDTO> getAllRooms();

	    List<RoomResponseDTO> getRoomsByHotel(Long hotelId);

	    RoomResponseDTO updateRoom(Long id, RoomRequestDTO dto);

	    void deleteRoom(Long id);

	    RoomResponseDTO updateRoomAvailability(Long roomId, boolean available);
	    List<RoomResponseDTO> getRoomsByAvailability(Boolean available);

	    List<RoomResponseDTO> getRoomsByRoomType(Long roomTypeId);

	    RoomResponseDTO addAmenityToRoom(Long roomId, Long amenityId);

	    RoomResponseDTO removeAmenityFromRoom(Long roomId, Long amenityId);

	    List<Long> getAmenitiesByRoom(Long roomId);
	    
}
