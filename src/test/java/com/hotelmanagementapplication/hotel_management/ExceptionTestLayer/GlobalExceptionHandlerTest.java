package com.hotelmanagementapplication.hotel_management.ExceptionTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.hotelmanagementapplication.hotel_management.ExceptionHandlerEntity.ErrorResponse;
import com.hotelmanagementapplication.hotel_management.ExceptionHandlerEntity.GlobalExceptionHandler;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.AmenityNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.HotelNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.ReservationNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.RoomNotFoundException;

@ExtendWith(MockitoExtension.class)
	public class GlobalExceptionHandlerTest {

	

	    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

	    @Test
	    void testHandleRoomNotFound() {
	        RoomNotFoundException ex = new RoomNotFoundException("Room 101 not found");
	        ResponseEntity<ErrorResponse> response = handler.handleRoomNotFound(ex);

	        assertEquals(404, response.getBody().getStatus());
	        assertEquals("Room Not Found", response.getBody().getError());
	        assertEquals("Room 101 not found", response.getBody().getMessage());
	    }

	    @Test
	    void testHandleHotelNotFound() {
	        HotelNotFoundException ex = new HotelNotFoundException("Hotel ABC not found");
	        ResponseEntity<ErrorResponse> response = handler.handleHotelNotFound(ex);

	        assertEquals(404, response.getBody().getStatus());
	        assertEquals("Hotel Not Found", response.getBody().getError());
	    }

	    @Test
	    void testHandleReservationNotFound() {
	        ReservationNotFoundException ex = new ReservationNotFoundException("Reservation XYZ not found");
	        ResponseEntity<ErrorResponse> response = handler.handleReservationNotFound(ex);

	        assertEquals(404, response.getBody().getStatus());
	        assertEquals("Reservation Not Found", response.getBody().getError());
	    }

	    @Test
	    void testHandleBadRequest() {
	        IllegalArgumentException ex = new IllegalArgumentException("Invalid input");
	        ResponseEntity<ErrorResponse> response = handler.handleBadRequest(ex);

	        assertEquals(400, response.getBody().getStatus());
	        assertEquals("Bad Request", response.getBody().getError());
	    }

	    @Test
	    void testHandleGenericException() {
	        Exception ex = new Exception("Unexpected error");
	        ResponseEntity<ErrorResponse> response = handler.handleGeneric(ex);

	        assertEquals(500, response.getBody().getStatus());
	        assertEquals("Internal Server Error", response.getBody().getError());
	    }

	    @Test
	    void testHandleAmenityNotFound() {
	        AmenityNotFoundException ex = new AmenityNotFoundException("Amenity pool not found");
	        ResponseEntity<ErrorResponse> response = handler.handleAmenityNotFound(ex);

	        assertEquals(404, response.getBody().getStatus());
	        assertEquals("Amenity Not Found", response.getBody().getError());
	    }
	}


