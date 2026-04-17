package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.RoomTypeController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomTypeRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomTypeService;

@ExtendWith(MockitoExtension.class)
	public class RoomTypeControllerTest {

	    @Mock
	    private RoomTypeService roomTypeService;

	    @InjectMocks
	    private RoomTypeController roomTypeController;

	    // ✅ CREATE
	    @Test
	    void shouldCreateRoomType() {

	        RoomTypeRequestDTO req = new RoomTypeRequestDTO();
	        req.setTypeName("Deluxe");
	        req.setPricePerNight(2500.0);
	        req.setMaxOccupancy(3);

	        RoomTypeResponseDTO res = new RoomTypeResponseDTO();
	        res.setId(1L);
	        res.setTypeName("Deluxe");

	        when(roomTypeService.addRoomType(any())).thenReturn(res);

	        RoomTypeResponseDTO result = roomTypeController.add(req);

	        assertNotNull(result);
	        assertEquals("Deluxe", result.getTypeName());
	    }

	    // ❌ INVALID CREATE
	    @Test
	    void shouldThrowExceptionForInvalidRoomType() {

	        when(roomTypeService.addRoomType(any()))
	                .thenThrow(new RuntimeException("Invalid"));

	        assertThrows(RuntimeException.class, () -> {
	            roomTypeController.add(new RoomTypeRequestDTO());
	        });
	    }

	    // ✅ GET BY ID
	    @Test
	    void shouldGetRoomTypeById() {

	        RoomTypeResponseDTO res = new RoomTypeResponseDTO();
	        res.setId(2L);

	        when(roomTypeService.getRoomTypeById(2L)).thenReturn(res);

	        RoomTypeResponseDTO result = roomTypeController.get(2L);

	        assertEquals(2L, result.getId());
	    }

	    // ❌ NOT FOUND
	    @Test
	    void shouldThrowExceptionWhenRoomTypeNotFound() {

	        when(roomTypeService.getRoomTypeById(99L))
	                .thenThrow(new RuntimeException("Not found"));

	        assertThrows(RuntimeException.class, () -> {
	            roomTypeController.get(99L);
	        });
	    }

	    // ✅ GET ALL
	    @Test
	    void shouldGetAllRoomTypes() {

	        when(roomTypeService.getAllRoomTypes())
	                .thenReturn(List.of(new RoomTypeResponseDTO(), new RoomTypeResponseDTO()));

	        List<RoomTypeResponseDTO> result = roomTypeController.getAll();

	        assertEquals(2, result.size());
	    }

	    // ❌ EMPTY
	    @Test
	    void shouldReturnEmptyList() {

	        when(roomTypeService.getAllRoomTypes())
	                .thenReturn(Collections.emptyList());

	        assertTrue(roomTypeController.getAll().isEmpty());
	    }

	    // ✅ UPDATE
	    @Test
	    void shouldUpdateRoomType() {

	        RoomTypeResponseDTO res = new RoomTypeResponseDTO();
	        res.setId(5L);

	        when(roomTypeService.updateRoomType(eq(5L), any()))
	                .thenReturn(res);

	        RoomTypeResponseDTO result =
	                roomTypeController.update(5L, new RoomTypeRequestDTO());

	        assertEquals(5L, result.getId());
	    }

	    // ❌ UPDATE INVALID
	    @Test
	    void shouldThrowExceptionWhenUpdatingInvalidRoomType() {

	        when(roomTypeService.updateRoomType(eq(50L), any()))
	                .thenThrow(new RuntimeException("Not found"));

	        assertThrows(RuntimeException.class, () -> {
	            roomTypeController.update(50L, new RoomTypeRequestDTO());
	        });
	    }

	    // ✅ DELETE
	    @Test
	    void shouldDeleteRoomType() {

	        doNothing().when(roomTypeService).deleteRoomType(1L);

	        String result = roomTypeController.delete(1L);

	        assertEquals("RoomType deleted successfully", result);
	    }
	}

