package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
	    void shouldCreateRoom() {

	        RoomRequestDTO req = new RoomRequestDTO();
	        req.setRoomNumber(101);

	        RoomResponseDTO res = new RoomResponseDTO();
	        res.setId(1L);
	        res.setRoomNumber(101);

	        when(roomService.addRoom(any())).thenReturn(res);

	        RoomResponseDTO result = roomController.addRoom(req);

	        assertNotNull(result);
	        assertEquals(101, result.getRoomNumber());
	    }

	    // ❌ INVALID CREATE
	    @Test
	    void shouldThrowExceptionWhenInvalidRoom() {

	        when(roomService.addRoom(any()))
	                .thenThrow(new RuntimeException("Invalid room"));

	        assertThrows(RuntimeException.class, () -> {
	            roomController.addRoom(new RoomRequestDTO());
	        });
	    }

	    // ✅ GET BY ID
	    @Test
	    void shouldGetRoomById() {

	        RoomResponseDTO res = new RoomResponseDTO();
	        res.setId(2L);

	        when(roomService.getRoomById(2L)).thenReturn(res);

	        RoomResponseDTO result = roomController.getRoom(2L);

	        assertEquals(2L, result.getId());
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

