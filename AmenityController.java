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

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.AmenityService;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    // CREATE
    @PostMapping
    public AmenityDTO add(@RequestBody AmenityDTO dto) {
        return amenityService.addAmenity(dto);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public AmenityDTO get(@PathVariable Long id) {
        return amenityService.getAmenityById(id);
    }

    // GET ALL
    @GetMapping
    public List<AmenityDTO> getAll() {
        return amenityService.getAllAmenities();
    }

    // UPDATE
    @PutMapping("/{id}")
    public AmenityDTO update(@PathVariable Long id,
                             @RequestBody AmenityDTO dto) {
        return amenityService.updateAmenity(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return "Amenity deleted successfully";
    }

}
