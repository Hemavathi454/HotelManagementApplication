package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.HotelResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.HotelNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.HotelMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.HotelRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.HotelService;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{
	

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public HotelResponseDTO addHotel(HotelRequestDTO dto) {

        List<Amenity> amenities = null;

        if (dto.getAmenityIds() != null && !dto.getAmenityIds().isEmpty()) {
            amenities = amenityRepository.findAllById(dto.getAmenityIds());
        }

        Hotel hotel = HotelMapper.toEntity(dto, amenities);

        return HotelMapper.toDTO(hotelRepository.save(hotel));
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        return HotelMapper.toDTO(hotel);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels() {

        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper::toDTO)
                .toList();
    }

    @Override
    public List<HotelResponseDTO> getHotelsByLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        return hotelRepository.findByLocation(location)
                .stream()
                .map(HotelMapper::toDTO)
                .toList();
    }

    @Override
    public List<HotelResponseDTO> searchHotelsByName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be empty");
        }

        return hotelRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(HotelMapper::toDTO)
                .toList();
    }

    @Override
    public HotelResponseDTO updateHotel(Long id, HotelRequestDTO dto) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
        hotel.setDescription(dto.getDescription());

        if (dto.getAmenityIds() != null) {
            List<Amenity> amenities = amenityRepository.findAllById(dto.getAmenityIds());
            hotel.setAmenities(amenities);
        }

        return HotelMapper.toDTO(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

        hotelRepository.delete(hotel);
    }
	}


