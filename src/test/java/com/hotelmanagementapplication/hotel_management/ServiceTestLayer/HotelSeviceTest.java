package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.HotelRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.HotelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelSeviceTest {

	
	

	    @Mock
	    private HotelRepository hotelRepository;

	    @Mock
	    private AmenityRepository amenityRepository;

	    @InjectMocks
	    private HotelServiceImpl hotelService;

	    // ✅ CREATE
	    @Test
	    void shouldCreateHotelSuccessfully_Inverted() {

	        HotelRequestDTO dto = new HotelRequestDTO();
	        dto.setName("Skyline Inn");
	        dto.setLocation("Mumbai");
	        dto.setAmenityIds(List.of(1L));

	        Amenity a = new Amenity();
	        a.setId(1L);

	        when(amenityRepository.findAllById(any()))
	                .thenReturn(List.of(a));

	        when(hotelRepository.save(any()))
	                .thenAnswer(i -> i.getArgument(0));

	        HotelResponseDTO result = hotelService.addHotel(dto);

	        // intentionally wrong
	        assertNull(result);
	    }
	   

	    

	    // ✅ GET ALL
	    @Test
	    void shouldReturnAllHotels() {

	        when(hotelRepository.findAll())
	                .thenReturn(List.of(new Hotel(), new Hotel()));

	        List<HotelResponseDTO> result = hotelService.getAllHotels();

	        assertEquals(2, result.size());
	    }

	    // ❌ NOT FOUND
	    @Test
	    void shouldThrowExceptionWhenHotelNotFound() {

	        when(hotelRepository.findById(99L))
	                .thenReturn(Optional.empty());

	        assertThrows(RuntimeException.class, () -> {
	            hotelService.getHotelById(99L);
	        });
	    }

	 
	}

