package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.AmenityDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.MapperLayer.AmenityMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.AmenityService;

@Service
public class AmenityServiceImpl implements AmenityService{
	  @Autowired
	    private AmenityRepository amenityRepository;

	    // CREATE
	    @Override
	    public AmenityDTO addAmenity(AmenityDTO dto) {

	        Amenity a = AmenityMapper.toEntity(dto);

	        return AmenityMapper.toDTO(amenityRepository.save(a));
	    }

	    // GET BY ID
	    @Override
	    public AmenityDTO getAmenityById(Long id) {

	        Amenity a = amenityRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Amenity not found"));

	        return AmenityMapper.toDTO(a);
	    }

	    // GET ALL
	    @Override
	    public List<AmenityDTO> getAllAmenities() {

	        return amenityRepository.findAll()
	                .stream()
	                .map(AmenityMapper::toDTO)
	                .toList();
	    }

	    // UPDATE
	    @Override
	    public AmenityDTO updateAmenity(Long id, AmenityDTO dto) {

	        Amenity a = amenityRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Amenity not found"));

	        a.setName(dto.getName());
	        a.setDescription(dto.getDescription());

	        return AmenityMapper.toDTO(amenityRepository.save(a));
	    }

	    // DELETE
	    @Override
	    public void deleteAmenity(Long id) {

	        Amenity a = amenityRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Amenity not found"));

	        amenityRepository.delete(a);
	    }

}

