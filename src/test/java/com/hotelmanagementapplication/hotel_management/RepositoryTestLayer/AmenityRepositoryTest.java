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
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
	    @Test
	    void shouldReturnEmptyForInvalidId_Inverted() {

	        Optional<Amenity> found =
	                amenityRepository.findById(999L);

	        // wrong expectation
	        assertFalse(found.isEmpty());
	    }

	    @Test
	    void shouldReturnEmptyWhenNoAmenities_Inverted() {

	        List<Amenity> list = amenityRepository.findAll();

	        // wrong expectation
	        assertFalse(list.isEmpty());
	    }

	}

