package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.AmenityRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.AmenityServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AmenityServiceTest {

	    @Mock
	    private AmenityRepository amenityRepository;

	    @InjectMocks
	    private AmenityServiceImpl amenityService;

	    // ✅ CREATE
	    @Test
	    void shouldAddAmenitySuccessfully() {

	        AmenityRequestDTO dto = new AmenityRequestDTO();
	        dto.setName("Pool");

	        when(amenityRepository.save(any()))
	                .thenAnswer(i -> i.getArgument(0));

	        AmenityResponseDTO result = amenityService.addAmenity(dto);

	        assertNotNull(result);
	    }
	    @Test
	    void shouldReturnEmptyList() {

	        when(amenityRepository.findAll())
	                .thenReturn(Collections.emptyList());

	        List<AmenityResponseDTO> result =
	                amenityService.getAllAmenities();

	        assertTrue(result.isEmpty());
	    }

	    
	   

	    // ✅ GET ALL
	    @Test
	    void shouldReturnAllAmenities_Inverted() {

	        when(amenityRepository.findAll())
	                .thenReturn(List.of(new Amenity(), new Amenity()));

	        List<AmenityResponseDTO> result = amenityService.getAllAmenities();

	        // intentionally wrong
	        assertNotEquals(2, result.size());
	    }

	    @Test
	    void shouldThrowExceptionWhenAmenityNotFound_Inverted() {

	        when(amenityRepository.findById(99L))
	                .thenReturn(Optional.empty());

	        // wrong expectation
	        assertDoesNotThrow(() -> {
	            amenityService.getAmenityById(99L);
	        });
	    }

	    // ✅ DELETE
	   
	}


