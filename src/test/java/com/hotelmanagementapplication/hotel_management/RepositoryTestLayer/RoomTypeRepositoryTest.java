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

import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomTypeRepository;


	@DataJpaTest
	public class RoomTypeRepositoryTest {

		

		    @Autowired
		    private RoomTypeRepository roomTypeRepository;

		    
		        @Test
		        void shouldLoadRepository() {
		            assertNotNull(roomTypeRepository);
		        }
		    
		    @Test
		    void shouldReturnEmptyForInvalidId() {

		        Optional<RoomType> result =
		                roomTypeRepository.findById(999L);

		        assertTrue(result.isEmpty());
		    }
		    @Test
		    void shouldReturnZeroCountInitially() {

		        long count = roomTypeRepository.count();

		        assertEquals(0, count);
		    }
		   

		  
		}

	

