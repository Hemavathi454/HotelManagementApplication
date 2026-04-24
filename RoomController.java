package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomResponseDTO;

import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomService;

import jakarta.validation.Valid;

@RestController //handle API request---- return JSON
@RequestMapping("/rooms")  
public class RoomController {

    @Autowired //spring creates an object
    private RoomService roomService;

    // CREATE
    @PostMapping // add new data
    public RoomResponseDTO addRoom(@Valid @RequestBody RoomRequestDTO dto) {
        return roomService.addRoom(dto);
    }

    // GET BY ID
    @GetMapping("/{roomId}")  //fetch data 
    public RoomResponseDTO getRoom(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId);
    }

    // ✅ UPDATED: GET ALL WITH FILTERS
    @GetMapping 
    public List<RoomResponseDTO> getAll(
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) Long roomTypeId) {

        if (available != null) {
            return roomService.getRoomsByAvailability(available);
        }

        if (roomTypeId != null) {
            return roomService.getRoomsByRoomType(roomTypeId);
        }

        return roomService.getAllRooms();
    }

    // GET BY HOTEL
    @GetMapping("/hotel/{hotelId}")
    public List<RoomResponseDTO> getByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    // UPDATE
    @PutMapping("/{roomId}")
    public RoomResponseDTO update(@PathVariable Long roomId,
                                 @Valid @RequestBody RoomRequestDTO dto) {
        return roomService.updateRoom(roomId, dto);
    }

    // DELETE
    @DeleteMapping("/{roomId}")
    public String delete(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return "Room deleted successfully";
    }

    // UPDATE AVAILABILITY
    @PatchMapping("/{roomId}/availability")
    public RoomResponseDTO updateAvailability(@PathVariable Long roomId,
                                             @RequestParam boolean available) {
        return roomService.updateRoomAvailability(roomId, available);
    }

    // ✅ ADD AMENITY TO ROOM
    @PostMapping("/{roomId}/amenities/{amenityId}")
    public RoomResponseDTO addAmenity(@PathVariable Long roomId,
                                     @PathVariable Long amenityId) {
        return roomService.addAmenityToRoom(roomId, amenityId);
    }

    // ✅ REMOVE AMENITY FROM ROOM
    @DeleteMapping("/{roomId}/amenities/{amenityId}")
    public RoomResponseDTO removeAmenity(@PathVariable Long roomId,
                                         @PathVariable Long amenityId) {
        return roomService.removeAmenityFromRoom(roomId, amenityId);
    }

    // ✅ GET ROOM AMENITIES
    @GetMapping("/{roomId}/amenities")
    public List<Long> getAmenities(@PathVariable Long roomId) {
        return roomService.getAmenitiesByRoom(roomId);
    }
}
