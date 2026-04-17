package com.hotelmanagementapplication.hotel_management.RepositoryTestLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;

@DataJpaTest
	public class RoomRepositoryTest {

	    @Autowired
	    private RoomRepository roomRepository;

	    // ✅ LOAD
	    @Test
	    void shouldLoadRepository() {
	        assertNotNull(roomRepository);
	    }

	    // ✅ EMPTY
	    @Test
	    void shouldReturnEmptyInitially() {

	        List<Room> list = roomRepository.findAll();

	        assertTrue(list.isEmpty());
	    }

	    // ✅ COUNT
	    @Test
	    void shouldReturnZeroCount() {

	        long count = roomRepository.count();

	        assertEquals(0, count);
	    }

	    // ❌ INVALID ID
	    @Test
	    void shouldReturnEmptyForInvalidId() {

	        Optional<Room> result = roomRepository.findById(999L);

	        assertTrue(result.isEmpty());
	    }
	}

	


