package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityResponseDTO;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.AmenityRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.AmenityService;

import jakarta.validation.Valid;

		@SuppressWarnings("unused")
		@RestController
		@RequestMapping("/amenities")
    public class AmenityController {
        @Autowired
        private AmenityService amenityService;

        // CREATE
        @PostMapping
        public AmenityResponseDTO add(@Valid @RequestBody AmenityRequestDTO dto) {
            return amenityService.addAmenity(dto);
        }

        // GET BY ID
        @GetMapping("/{id}")
        public AmenityResponseDTO get(@PathVariable Long id) {
            return amenityService.getAmenityById(id);
        }

        // GET ALL
        @GetMapping
        public List<AmenityResponseDTO> getAll() {
            return amenityService.getAllAmenities();
        }

        // UPDATE
        @PutMapping("/{id}")
        public AmenityResponseDTO update(@PathVariable Long id,
                                         @Valid @RequestBody AmenityRequestDTO dto) {
            return amenityService.updateAmenity(id, dto);
        }

        // DELETE
        @DeleteMapping("/{id}")
        public String delete(@PathVariable Long id) {
            amenityService.deleteAmenity(id);
            return "Amenity deleted successfully";
        }

        // ROOM ↔ AMENITY (keep as is)
        @PostMapping("/rooms/{roomId}/amenities/{amenityId}")
        public String assignToRoom(@PathVariable Long roomId,
                                   @PathVariable Long amenityId) {
            amenityService.assignAmenityToRoom(roomId, amenityId);
            return "Amenity assigned to room";
        }

        @DeleteMapping("/rooms/{roomId}/amenities/{amenityId}")
        public String removeFromRoom(@PathVariable Long roomId,
                                     @PathVariable Long amenityId) {
            amenityService.removeAmenityFromRoom(roomId, amenityId);
            return "Amenity removed from room";
        }

        @GetMapping("/rooms/{roomId}/amenities")
        public List<AmenityResponseDTO> getRoomAmenities(@PathVariable Long roomId) {
            return amenityService.getAmenitiesByRoom(roomId);
        }
    }

