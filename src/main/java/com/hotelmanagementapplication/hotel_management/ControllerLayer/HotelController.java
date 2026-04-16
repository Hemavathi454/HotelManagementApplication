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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.MapperLayer.HotelMapper;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.HotelRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // POST create
    @PostMapping
    public HotelResponseDTO addHotel(@Valid   @RequestBody HotelRequestDTO dto) {
        return hotelService.addHotel(dto);
    }

    // GET ALL
   
    @GetMapping
    public List<HotelResponseDTO> getAllHotels(
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (location != null) {
            return hotelService.getHotelsByLocation(location);
        }
        return hotelService.getAllHotels();
    }

    // GET BY ID
    @GetMapping("/{hotelId}")
    public HotelResponseDTO getHotel(@PathVariable Long hotelId) {
        return hotelService.getHotelById(hotelId);
    }

    // UPDATE
    @PutMapping("/{hotelId}")
    public HotelResponseDTO updateHotel(@PathVariable Long hotelId,
                                        @Valid @RequestBody HotelRequestDTO dto) {
        return hotelService.updateHotel(hotelId, dto);
    }

    // DELETE
    @DeleteMapping("/{hotelId}")
    public String deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotel(hotelId);
        return "Hotel deleted successfully";
    }

}
