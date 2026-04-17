package com.hotelmanagementapplication.hotel_management.RepositoryTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;

@DataJpaTest
	public class HotelRepositoryTest {

	    @Autowired
	    private HotelRepository hotelRepository;

	    // ✅ LOAD
	    @Test
	    void shouldLoadRepository() {
	        assertNotNull(hotelRepository);
	    }

	    // ✅ EMPTY LIST
	    @Test
	    void shouldReturnEmptyInitially() {

	        List<Hotel> list = hotelRepository.findAll();

	        assertTrue(list.isEmpty());
	    }

	    // ✅ COUNT
	    @Test
	    void shouldReturnZeroCount() {

	        long count = hotelRepository.count();

	        assertEquals(0, count);
	    }

	    // ❌ INVALID ID
	    @Test
	    void shouldReturnEmptyForInvalidId() {

	        Optional<Hotel> result = hotelRepository.findById(999L);

	        assertTrue(result.isEmpty());
	    }
	}

	

