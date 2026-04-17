package com.hotelmanagementapplication.hotel_management.ControllerTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.ControllerLayer.AmenityController;
import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.AmenityRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.AmenityService;

@ExtendWith(MockitoExtension.class)
public class AmenityTestController {
	
	
	

	    @Mock
	    private AmenityService amenityService;

	    @InjectMocks
	    private AmenityController amenityController;

	    // ✅ CREATE
	    @Test
	    void shouldCreateAmenity() {

	        AmenityRequestDTO req = new AmenityRequestDTO();
	        req.setName("WiFi");

	        AmenityResponseDTO res = new AmenityResponseDTO();
	        res.setId(1L);
	        res.setName("WiFi");

	        when(amenityService.addAmenity(any())).thenReturn(res);

	        AmenityResponseDTO result = amenityController.add(req);

	        assertNotNull(result);
	        assertEquals("WiFi", result.getName());
	    }

	    // ❌ INVALID CREATE
	    @Test
	    void shouldThrowExceptionForInvalidAmenity() {

	        when(amenityService.addAmenity(any()))
	                .thenThrow(new RuntimeException("Invalid"));

	        assertThrows(RuntimeException.class, () -> {
	            amenityController.add(new AmenityRequestDTO());
	        });
	    }

	    // ✅ GET ALL
	    @Test
	    void shouldGetAllAmenities() {

	        when(amenityService.getAllAmenities())
	                .thenReturn(List.of(new AmenityResponseDTO(), new AmenityResponseDTO()));

	        List<AmenityResponseDTO> result = amenityController.getAll();

	        assertEquals(2, result.size());
	    }

	    // ❌ EMPTY
	    @Test
	    void shouldReturnEmptyWhenNoAmenities() {

	        when(amenityService.getAllAmenities())
	                .thenReturn(Collections.emptyList());

	        assertTrue(amenityController.getAll().isEmpty());
	    }

	    // ✅ GET BY ID
	    @Test
	    void shouldGetAmenityById() {

	        AmenityResponseDTO res = new AmenityResponseDTO();
	        res.setId(1L);

	        when(amenityService.getAmenityById(1L)).thenReturn(res);

	        AmenityResponseDTO result = amenityController.get(1L);

	        assertEquals(1L, result.getId());
	    }

	    // ❌ NOT FOUND
	    @Test
	    void shouldThrowExceptionWhenAmenityNotFound() {

	        when(amenityService.getAmenityById(99L))
	                .thenThrow(new RuntimeException("Not found"));

	        assertThrows(RuntimeException.class, () -> {
	            amenityController.get(99L);
	        });
	    }

	    // ✅ DELETE
	    @Test
	    void shouldDeleteAmenity() {

	        doNothing().when(amenityService).deleteAmenity(1L);

	        String result = amenityController.delete(1L);

	        assertEquals("Amenity deleted successfully", result);
	    }

	    // ❌ DELETE INVALID
	    @Test
	    void shouldThrowExceptionWhenDeletingInvalidAmenity() {

	        doThrow(new RuntimeException("Not found"))
	                .when(amenityService).deleteAmenity(99L);

	        assertThrows(RuntimeException.class, () -> {
	            amenityController.delete(99L);
	        });
	    }
	    @Test
	    void shouldThrowExceptionWhenAmenityNotFound_Inverted() {

	        when(amenityService.getAmenityById(99L))
	                .thenThrow(new RuntimeException("Not found"));

	        // wrong expectation → will fail if system works correctly
	        assertDoesNotThrow(() -> {
	            amenityController.get(99L);
	        });
	    }
	    @Test
	    void shouldDeleteAmenity_Inverted() {

	        doNothing().when(amenityService).deleteAmenity(1L);

	        String result = amenityController.delete(1L);

	        // intentionally wrong → fail if actual is correct
	        assertNotEquals("Amenity deleted successfully", result);
	    }
	}
	

