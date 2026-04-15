package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.HotelNotFoundException;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.HotelService;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{
	

	

	    @Autowired
	    private HotelRepository hotelRepository;

	    @Autowired
	    private AmenityRepository amenityRepository;

	    @Autowired
	    private RoomRepository roomRepository;

	    // ================= ADD HOTEL =================
	    public HotelDTO addHotel(HotelDTO dto) {

	        validateHotel(dto);

	        Hotel hotel = new Hotel();
	        hotel.setName(dto.getName());
	        hotel.setLocation(dto.getLocation());
	        hotel.setDescription(dto.getDescription());

	        // Amenities (IDs → Entities)
	        if (dto.getAmenityIds() != null) {
	            List<Amenity> amenities = amenityRepository.findAllById(dto.getAmenityIds());
	            hotel.setAmenities(amenities);
	        }

	        Hotel saved = hotelRepository.save(hotel);

	        return mapToDTO(saved);
	    }

	    // ================= GET BY ID =================
	    public HotelDTO getHotelById(Long id) {

	        Hotel hotel = hotelRepository.findById(id)
	                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

	        return mapToDTO(hotel);
	    }

	    // ================= GET ALL =================
	    public List<HotelDTO> getAllHotels() {

	        return hotelRepository.findAll()
	                .stream()
	                .map(this::mapToDTO)
	                .toList();
	    }

	    // ================= BY LOCATION =================
	    public List<HotelDTO> getHotelsByLocation(String location) {

	        if (location == null || location.trim().isEmpty()) {
	            throw new IllegalArgumentException("Location cannot be empty");
	        }

	        return hotelRepository.findByLocation(location)
	                .stream()
	                .map(this::mapToDTO)
	                .toList();
	    }

	    // ================= SEARCH BY NAME =================
	    public List<HotelDTO> searchHotelsByName(String name) {

	        if (name == null || name.trim().isEmpty()) {
	            throw new IllegalArgumentException("Hotel name cannot be empty");
	        }

	        return hotelRepository.findByNameContainingIgnoreCase(name)
	                .stream()
	                .map(this::mapToDTO)
	                .toList();
	    }

	    // ================= UPDATE =================
	    public HotelDTO updateHotel(Long id, HotelDTO dto) {

	        Hotel hotel = hotelRepository.findById(id)
	                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

	        validateHotel(dto);

	        hotel.setName(dto.getName());
	        hotel.setLocation(dto.getLocation());
	        hotel.setDescription(dto.getDescription());

	        if (dto.getAmenityIds() != null) {
	            List<Amenity> amenities = amenityRepository.findAllById(dto.getAmenityIds());
	            hotel.setAmenities(amenities);
	        }

	        return mapToDTO(hotelRepository.save(hotel));
	    }

	    // ================= DELETE =================
	    public void deleteHotel(Long id) {

	        Hotel hotel = hotelRepository.findById(id)
	                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

	        hotelRepository.delete(hotel);
	    }

	    // ================= MAPPER =================
	    private HotelDTO mapToDTO(Hotel hotel) {

	        HotelDTO dto = new HotelDTO();
	        dto.setId(hotel.getId());
	        dto.setName(hotel.getName());
	        dto.setLocation(hotel.getLocation());
	        dto.setDescription(hotel.getDescription());

	        // Amenity IDs only (prevents recursion)
	        if (hotel.getAmenities() != null) {
	            dto.setAmenityIds(
	                    hotel.getAmenities()
	                            .stream()
	                            .map(Amenity::getId)
	                            .toList()
	            );
	        }

	        // Room IDs only (safe)
	        if (hotel.getRooms() != null) {
	            dto.setRoomIds(
	                    hotel.getRooms()
	                            .stream()
	                            .map(Room::getId)
	                            .toList()
	            );
	        }

	        return dto;
	    }

	    // ================= VALIDATION =================
	    private void validateHotel(HotelDTO dto) {

	        if (dto == null) {
	            throw new IllegalArgumentException("Hotel cannot be null");
	        }

	        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
	            throw new IllegalArgumentException("Hotel name is required");
	        }

	        if (dto.getLocation() == null || dto.getLocation().trim().isEmpty()) {
	            throw new IllegalArgumentException("Hotel location is required");
	        }

	        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
	            throw new IllegalArgumentException("Hotel description is required");
	        }
	    }
	}

