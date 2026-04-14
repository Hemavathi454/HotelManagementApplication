package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomTypeDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.MapperLayer.RoomTypeMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomTypeRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomTypeService;
@Service
public class RoomTypeServiceImpl implements RoomTypeService{

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    // CREATE
    @Override
    public RoomTypeDTO addRoomType(RoomTypeDTO dto) {

        RoomType r = RoomTypeMapper.toEntity(dto);

        return RoomTypeMapper.toDTO(roomTypeRepository.save(r));
    }

    // GET BY ID
    @Override
    public RoomTypeDTO getRoomTypeById(Long id) {

        RoomType r = roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));

        return RoomTypeMapper.toDTO(r);
    }

    // GET ALL
    @Override
    public List<RoomTypeDTO> getAllRoomTypes() {

        return roomTypeRepository.findAll()
                .stream()
                .map(RoomTypeMapper::toDTO)
                .toList();
    }

    // UPDATE
    @Override
    public RoomTypeDTO updateRoomType(Long id, RoomTypeDTO dto) {

        RoomType r = roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));

        r.setTypeName(dto.getTypeName());
        r.setDescription(dto.getDescription());
        r.setMaxOccupancy(dto.getMaxOccupancy());
        r.setPricePerNight(dto.getPricePerNight());

        return RoomTypeMapper.toDTO(roomTypeRepository.save(r));
    }

    // DELETE
    @Override
    public void deleteRoomType(Long id) {

        RoomType r = roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));

        roomTypeRepository.delete(r);
    }

}
