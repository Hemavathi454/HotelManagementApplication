package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Amenity;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Hotel;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.RoomType;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.HotelNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.RoomNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.RoomMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.AmenityRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.HotelRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomTypeRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    // CREATE
    @Override
    public RoomDTO addRoom(RoomDTO dto) {

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        List<Amenity> amenities = (dto.getAmenityIds() == null)
                ? new ArrayList<>()
                : amenityRepository.findAllById(dto.getAmenityIds());

        Room room = RoomMapper.toEntity(dto, hotel, roomType, amenities);

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    // GET BY ID
    @Override
    public RoomDTO getRoomById(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));

        return RoomMapper.toDTO(room);
    }

    // GET ALL
    @Override
    public List<RoomDTO> getAllRooms() {

        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    // GET BY HOTEL
    @Override
    public List<RoomDTO> getRoomsByHotel(Long hotelId) {

        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    // UPDATE
    @Override
    public RoomDTO updateRoom(Long id, RoomDTO dto) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        List<Amenity> amenities = amenityRepository.findAllById(dto.getAmenityIds());

        room.setRoomNumber(dto.getRoomNumber());
        room.setIsAvailable(dto.getIsAvailable());
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setAmenities(amenities);

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    // DELETE
    @Override
    public void deleteRoom(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));

        roomRepository.delete(room);
    }
}

