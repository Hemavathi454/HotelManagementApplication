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

import com.hotelmanagementapplication.hotel_management.ControllerLayer.HotelController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.HotelRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.HotelService;

@ExtendWith(MockitoExtension.class)
public class HotelTestController {
	
	

	    @Mock
	    private HotelService hotelService;

	    @InjectMocks
	    private HotelController hotelController;

	    // ✅ CREATE
	    @Test
	    void shouldCreateHotel() {

	        HotelRequestDTO req = new HotelRequestDTO();
	        req.setName("Grand Palace");
	        req.setLocation("Chennai");

	        HotelResponseDTO res = new HotelResponseDTO();
	        res.setId(1L);
	        res.setName("Grand Palace");

	        when(hotelService.addHotel(any())).thenReturn(res);

	        HotelResponseDTO result = hotelController.addHotel(req);

	        assertNotNull(result);
	        assertEquals("Grand Palace", result.getName());
	    }

	    // ❌ INVALID CREATE
	    @Test
	    void shouldThrowExceptionForInvalidHotel() {

	        when(hotelService.addHotel(any()))
	                .thenThrow(new RuntimeException("Invalid hotel"));

	        assertThrows(RuntimeException.class, () -> {
	            hotelController.addHotel(new HotelRequestDTO());
	        });
	    }

	    // ✅ GET BY ID
	    @Test
	    void shouldGetHotelById() {

	        HotelResponseDTO res = new HotelResponseDTO();
	        res.setId(2L);

	        when(hotelService.getHotelById(2L)).thenReturn(res);

	        HotelResponseDTO result = hotelController.getHotel(2L);

	        assertEquals(2L, result.getId());
	    }

	    // ❌ NOT FOUND
	    @Test
	    void shouldThrowExceptionWhenHotelNotFound() {

	        when(hotelService.getHotelById(99L))
	                .thenThrow(new RuntimeException("Hotel not found"));

	        assertThrows(RuntimeException.class, () -> {
	            hotelController.getHotel(99L);
	        });
	    }

	    
	   
	    // ❌ EMPTY
	  
	    @Test
	    void shouldDeleteHotel() {

	        doNothing().when(hotelService).deleteHotel(1L);

	        String result = hotelController.deleteHotel(1L);

	        assertEquals("Hotel deleted successfully", result);
	    }
	}


