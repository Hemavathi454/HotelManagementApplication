package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.MapperLayer.RoomTypeMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomTypeRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomTypeRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomTypeService;
@Service
public class RoomTypeServiceImpl implements RoomTypeService{

	 @Autowired
	    private RoomTypeRepository roomTypeRepository;

	    @Override
	    public RoomTypeResponseDTO addRoomType(RoomTypeRequestDTO dto) {

	        RoomType r = RoomTypeMapper.toEntity(dto);
	        return RoomTypeMapper.toDTO(roomTypeRepository.save(r));
	    }

	    @Override
	    public RoomTypeResponseDTO getRoomTypeById(Long id) {

	        RoomType r = roomTypeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("RoomType not found with id: " + id));

	        return RoomTypeMapper.toDTO(r);
	    }

	    @Override
	    public List<RoomTypeResponseDTO> getAllRoomTypes() {

	        return roomTypeRepository.findAll()
	                .stream()
	                .map(RoomTypeMapper::toDTO)
	                .toList();
	    }

	    @Override
	    public RoomTypeResponseDTO updateRoomType(Long id, RoomTypeRequestDTO dto) {

	        RoomType r = roomTypeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("RoomType not found with id: " + id));

	        r.setTypeName(dto.getTypeName());
	        r.setDescription(dto.getDescription());
	        r.setMaxOccupancy(dto.getMaxOccupancy());
	        r.setPricePerNight(dto.getPricePerNight());

	        return RoomTypeMapper.toDTO(roomTypeRepository.save(r));
	    }

	    @Override
	    public void deleteRoomType(Long id) {

	        RoomType r = roomTypeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("RoomType not found with id: " + id));

	        roomTypeRepository.delete(r);
	    }
}
