package com.hotelmanagementapplication.hotel_management.ControllerLayer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.MapperLayer.HotelMapper;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
@Autowired
private HotelService hotelService;

// CREATE
@PostMapping
public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO) {
    return hotelService.addHotel(hotelDTO);
}

// GET BY ID
@GetMapping("/{id}")
public HotelDTO getHotel(@PathVariable Long id) {
    return hotelService.getHotelById(id);
}

// GET ALL
@GetMapping
public List<HotelDTO> getAll() {
    return hotelService.getAllHotels();
}

// LOCATION SEARCH
@GetMapping("/location/{location}")
public List<HotelDTO> getByLocation(@PathVariable String location) {
    return hotelService.getHotelsByLocation(location);
}

// NAME SEARCH
@GetMapping("/search/{name}")
public List<HotelDTO> search(@PathVariable String name) {
    return hotelService.searchHotelsByName(name);
}

// UPDATE
@PutMapping("/{id}")
public HotelDTO update(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
    return hotelService.updateHotel(id, hotelDTO);
}

// DELETE
@DeleteMapping("/{id}")
public String delete(@PathVariable Long id) {
    hotelService.deleteHotel(id);
    return "Hotel deleted successfully";
}

}
