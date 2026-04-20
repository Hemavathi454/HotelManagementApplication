package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.RoomResponseDTO;
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
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.RoomRequestDTO;
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
    public RoomResponseDTO addRoom(RoomRequestDTO dto) {

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RoomNotFoundException("RoomType not found"));

        List<Amenity> amenities = (dto.getAmenityIds() == null)
                ? new ArrayList<>()
                : amenityRepository.findAllById(dto.getAmenityIds());

        Room room = RoomMapper.toEntity(dto, hotel, roomType, amenities);

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    // GET BY ID
    @Override
    public RoomResponseDTO getRoomById(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + id));

        return RoomMapper.toDTO(room);
    }

    // GET ALL
    @Override
    public List<RoomResponseDTO> getAllRooms() {

        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    // GET BY HOTEL
    @Override
    public List<RoomResponseDTO> getRoomsByHotel(Long hotelId) {

        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    // UPDATE
    @Override
    public RoomResponseDTO updateRoom(Long id, RoomRequestDTO dto) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RoomNotFoundException("RoomType not found"));

        List<Amenity> amenities = (dto.getAmenityIds() == null)
                ? new ArrayList<>()
                : amenityRepository.findAllById(dto.getAmenityIds());

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
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        roomRepository.delete(room);
    }

    // PATCH
    @Override
    public RoomResponseDTO updateRoomAvailability(Long roomId, boolean available) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        room.setIsAvailable(available);

        return RoomMapper.toDTO(roomRepository.save(room));
    }
    @Override
    public List<RoomResponseDTO> getRoomsByAvailability(Boolean available) {
        return roomRepository.findByIsAvailable(available)
                .stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    @Override
    public List<RoomResponseDTO> getRoomsByRoomType(Long roomTypeId) {
        return roomRepository.findAll()
                .stream()
                .filter(r -> r.getRoomType() != null &&
                             r.getRoomType().getId().equals(roomTypeId))
                .map(RoomMapper::toDTO)
                .toList();
    }

    @Override
    public RoomResponseDTO addAmenityToRoom(Long roomId, Long amenityId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        room.getAmenities().add(amenity);

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    @Override
    public RoomResponseDTO removeAmenityFromRoom(Long roomId, Long amenityId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.getAmenities().removeIf(a -> a.getId().equals(amenityId));

        return RoomMapper.toDTO(roomRepository.save(room));
    }

    @Override
    public List<Long> getAmenitiesByRoom(Long roomId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return room.getAmenities()
                .stream()
                .map(Amenity::getId)
                .toList();
    }
}
