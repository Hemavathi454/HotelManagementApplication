package com.hotelmanagementapplication.hotel_management.RepositoryTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;

@DataJpaTest
public class AmenityRepositoryTest {

	
	

	    @Autowired
	    private AmenityRepository amenityRepository;

	   
	    @Test
	    void shouldReturnEmptyForInvalidId() {

	        Optional<Amenity> found =
	                amenityRepository.findById(999L);

	        assertTrue(found.isEmpty());
	    }
	    // ❌ EMPTY
	    @Test
	    void shouldReturnEmptyWhenNoAmenities() {

	        List<Amenity> list = amenityRepository.findAll();

	        assertTrue(list.isEmpty());
	    }
	    @Test
	    void shouldLoadRepository() {
	        assertNotNull(amenityRepository);
	        
	    }
	    @Test
	    void shouldReturnZeroCount() {

	        long count = amenityRepository.count();

	        assertEquals(0, count);
	    }

	}

