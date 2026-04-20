package com.hotelmanagementapplication.hotel_management.ServiceTestLayer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomTypeRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomTypeRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceImplLayer.RoomTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
	public class RoomTypeServiceTest {

	    @Mock
	    private RoomTypeRepository roomTypeRepository;

	    @InjectMocks
	    private RoomTypeServiceImpl roomTypeService;

	    // ✅ CREATE
	    @Test
	    void shouldAddRoomTypeSuccessfully() {

	        RoomTypeRequestDTO dto = new RoomTypeRequestDTO();
	        dto.setTypeName("Suite");
	        dto.setPricePerNight(5000.0);
	        dto.setMaxOccupancy(4);

	        when(roomTypeRepository.save(any()))
	                .thenAnswer(i -> i.getArgument(0));

	        RoomTypeResponseDTO result = roomTypeService.addRoomType(dto);

	        assertNotNull(result);
	    }

	    // ❌ INVALID PRICE
	    

	    // ✅ GET ALL
	    @Test
	    void shouldReturnAllRoomTypes() {

	        when(roomTypeRepository.findAll())
	                .thenReturn(List.of(new RoomType(), new RoomType()));

	        List<RoomTypeResponseDTO> result =
	                roomTypeService.getAllRoomTypes();

	        assertEquals(2, result.size());
	    }

	    // ❌ NOT FOUND
	    @Test
	    void shouldFailIfExceptionThrownForInvalidRoomType() {

	        when(roomTypeRepository.findById(99L))
	                .thenReturn(Optional.empty());

	        // if exception happens → test FAILS
	        assertDoesNotThrow(() -> {
	            roomTypeService.getRoomTypeById(99L);
	        });
	    }

	   
	   
	}

	


