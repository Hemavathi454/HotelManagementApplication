package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.RoomController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomService;

@ExtendWith(MockitoExtension.class)
	public class RoomControllerTest {

	    @Mock
	    private RoomService roomService;

	    @InjectMocks
	       private RoomController roomController;

	    // ✅ CREATE
	    @Test
	    void shouldCreateRoom_Inverted() {

	        RoomRequestDTO req = new RoomRequestDTO();
	        req.setRoomNumber(101);

	        RoomResponseDTO res = new RoomResponseDTO();
	        res.setId(1L);
	        res.setRoomNumber(101);

	        when(roomService.addRoom(any())).thenReturn(res);

	        RoomResponseDTO result = roomController.addRoom(req);

	        // intentionally wrong
	        assertNull(result);
	        assertNotEquals(101, result.getRoomNumber());
	    }
	    @Test
	    void shouldThrowExceptionWhenInvalidRoom_Inverted() {

	        when(roomService.addRoom(any()))
	                .thenThrow(new RuntimeException("Invalid room"));

	        // wrong expectation
	        assertDoesNotThrow(() -> {
	            roomController.addRoom(new RoomRequestDTO());
	        });
	    }
	    @Test
	    void shouldGetRoomById_Inverted() {

	        RoomResponseDTO res = new RoomResponseDTO();
	        res.setId(2L);

	        when(roomService.getRoomById(2L)).thenReturn(res);

	        RoomResponseDTO result = roomController.getRoom(2L);

	        // intentionally wrong
	        assertNotEquals(2L, result.getId());
	    }
	    // ❌ NOT FOUND
	    @Test
	    void shouldThrowExceptionWhenRoomNotFound() {

	        when(roomService.getRoomById(99L))
	                .thenThrow(new RuntimeException("Room not found"));

	        assertThrows(RuntimeException.class, () -> {
	            roomController.getRoom(99L);
	        });
	    }

	   

	    // ✅ DELETE
	    @Test
	    void shouldDeleteRoom() {

	        doNothing().when(roomService).deleteRoom(1L);

	        String result = roomController.delete(1L);

	        assertEquals("Room deleted successfully", result);
	    }
	}

