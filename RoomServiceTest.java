package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomTypeRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.RoomServiceImpl;

@ExtendWith(MockitoExtension.class)
	public class RoomServiceTest {

	    @Mock
	    private RoomRepository roomRepository;

	    @Mock
	    private RoomTypeRepository roomTypeRepository;

	    @InjectMocks
	    private RoomServiceImpl roomService;

	
	   

	    // ❌ INVALID ROOM NUMBER
	    @Test
	    void shouldThrowExceptionForEmptyRoomNumber() {

	        RoomRequestDTO dto = new RoomRequestDTO();
	        dto.setRoomNumber(null);

	        assertThrows(RuntimeException.class, () -> {
	            roomService.addRoom(dto);
	        });
	    }

	    // ✅ GET ALL
	    @Test
	    void shouldFailIfAllRoomsReturnedCorrectly() {

	        when(roomRepository.findAll())
	                .thenReturn(List.of(new Room(), new Room()));

	        List<RoomResponseDTO> result = roomService.getAllRooms();

	        // if correct (size = 2) → test FAILS
	        assertNotEquals(2, result.size());
	    }

	    // ❌ FAIL if exception is correctly thrown
	    @Test
	    void shouldFailIfExceptionThrownForInvalidRoom() {

	        when(roomRepository.findById(99L))
	                .thenReturn(Optional.empty());

	        // if exception happens → test FAILS
	        assertDoesNotThrow(() -> {
	            roomService.getRoomById(99L);
	        });
	    }

	    
	  
	}

	

