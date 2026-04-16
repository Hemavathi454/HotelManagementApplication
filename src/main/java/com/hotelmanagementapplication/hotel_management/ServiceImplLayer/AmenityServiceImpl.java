package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.AmenityNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.RoomNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.AmenityMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.AmenityRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.AmenityService;
@Service
public class AmenityServiceImpl implements AmenityService{

	  @Autowired
	    private AmenityRepository amenityRepository;
	  @Autowired
	  private RoomRepository roomRepository;

	    // CREATE
	    @Override
	    public AmenityResponseDTO addAmenity(AmenityRequestDTO dto) {

	        Amenity a = AmenityMapper.toEntity(dto);

	        return AmenityMapper.toDTO(amenityRepository.save(a));
	    }

	    // GET BY ID
	    @Override
	    public AmenityResponseDTO getAmenityById(Long id) {

	        Amenity a = amenityRepository.findById(id)
	                .orElseThrow(() -> new AmenityNotFoundException("Amenity not found with id: " + id));

	        return AmenityMapper.toDTO(a);
	    }

	    // GET ALL
	    @Override
	    public List<AmenityResponseDTO> getAllAmenities() {

	        return amenityRepository.findAll()
	                .stream()
	                .map(AmenityMapper::toDTO)
	                .toList();
	    }

	    // UPDATE
	    @Override
	    public AmenityResponseDTO updateAmenity(Long id, AmenityRequestDTO dto) {

	        Amenity a = amenityRepository.findById(id)
	                .orElseThrow(() -> new AmenityNotFoundException("Amenity not found with id: " + id));

	        a.setName(dto.getName());
	        a.setDescription(dto.getDescription());

	        return AmenityMapper.toDTO(amenityRepository.save(a));
	    }

	    // DELETE
	    @Override
	    public void deleteAmenity(Long id) {

	        Amenity a = amenityRepository.findById(id)
	                .orElseThrow(() -> new AmenityNotFoundException("Amenity not found with id: " + id));

	        amenityRepository.delete(a);
	    }

	    @Override
	    public void assignAmenityToRoom(Long roomId, Long amenityId) {
	    	 Room room = roomRepository.findById(roomId)
	    	            .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

	    	    Amenity amenity = amenityRepository.findById(amenityId)
	    	            .orElseThrow(() -> new AmenityNotFoundException("Amenity not found with id: " + amenityId));

	    	    room.getAmenities().add(amenity);

	    	    roomRepository.save(room);
	    }

	    @Override
	    public void removeAmenityFromRoom(Long roomId, Long amenityId) {
	    	 Room room = roomRepository.findById(roomId)
	    	            .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

	    	    Amenity amenity = amenityRepository.findById(amenityId)
	    	            .orElseThrow(() -> new AmenityNotFoundException("Amenity not found with id: " + amenityId));

	    	    room.getAmenities().remove(amenity);

	    	    roomRepository.save(room);
	    }

	    @Override
	    public List<AmenityResponseDTO> getAmenitiesByRoom(Long roomId) {

	        Room room = roomRepository.findById(roomId)
	                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

	        return room.getAmenities()
	                .stream()
	                .map(AmenityMapper::toDTO)
	                .toList();
	        
	    }
}
