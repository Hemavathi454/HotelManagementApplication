package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.ReservationNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.RoomNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.ReservationMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	 @Autowired
	    private ReservationRepository reservationRepository;

	    @Autowired
	    private RoomRepository roomRepository;

	    @Autowired
	    private UserRepository userRepository;

	    // CREATE RESERVATION
	    @Override
	    public ReservationDTO createReservation(ReservationDTO dto) {

	        Room room = roomRepository.findById(dto.getRoomId())
	                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

	        Users user = userRepository.findById(dto.getUserId())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        // ❗ DATE VALIDATION
	        if (dto.getCheckInDate().isAfter(dto.getCheckOutDate())) {
	            throw new IllegalArgumentException("Check-in date must be before check-out date");
	        }

	        Reservation reservation = ReservationMapper.toEntity(dto, room, user);

	        return ReservationMapper.toDTO(reservationRepository.save(reservation));
	    }

	    // GET BY ID
	    @Override
	    public ReservationDTO getReservationById(Long id) {

	        Reservation r = reservationRepository.findById(id)
	                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

	        return ReservationMapper.toDTO(r);
	    }

	    // GET ALL
	    @Override
	    public List<ReservationDTO> getAllReservations() {

	        return reservationRepository.findAll()
	                .stream()
	                .map(ReservationMapper::toDTO)
	                .toList();
	    }

	    // GET BY USER
	    @Override
	    public List<ReservationDTO> getReservationsByUser(Long userId) {

	        return reservationRepository.findByUserId(userId)
	                .stream()
	                .map(ReservationMapper::toDTO)
	                .toList();
	    }

	    // UPDATE
	    @Override
	    public ReservationDTO updateReservation(Long id, ReservationDTO dto) {

	        Reservation r = reservationRepository.findById(id)
	                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

	        Room room = roomRepository.findById(dto.getRoomId())
	                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

	        Users user = userRepository.findById(dto.getUserId())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        r.setGuestName(dto.getGuestName());
	        r.setGuestEmail(dto.getGuestEmail());
	        r.setGuestPhone(dto.getGuestPhone());
	        r.setCheckInDate(dto.getCheckInDate());
	        r.setCheckOutDate(dto.getCheckOutDate());
	        r.setRoom(room);
	        r.setUser(user);

	        return ReservationMapper.toDTO(reservationRepository.save(r));
	    }

	    // DELETE
	    @Override
	    public void deleteReservation(Long id) {

	        Reservation r = reservationRepository.findById(id)
	                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

	        reservationRepository.delete(r);
	    }

}
