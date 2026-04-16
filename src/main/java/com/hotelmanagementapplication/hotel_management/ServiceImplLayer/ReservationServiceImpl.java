package com.hotelmanagementapplication.hotel_management.ServiceImplLayer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementapplication.hotel_management.DTOLayer.ReservationResponseDTO;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Reservation;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Room;
import com.hotelmanagementapplication.hotel_management.EntityClasses.Users;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.ReservationNotFoundException;
import com.hotelmanagementapplication.hotel_management.ExceptionLayer.RoomNotFoundException;
import com.hotelmanagementapplication.hotel_management.MapperLayer.ReservationMapper;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.ReservationRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.RoomRepository;
import com.hotelmanagementapplication.hotel_management.RepositoryLayer.UserRepository;
import com.hotelmanagementapplication.hotel_management.RequesDTOLayer.ReservationRequestDTO;
import com.hotelmanagementapplication.hotel_management.ServiceLayer.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
 ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        validateDates(dto.getCheckInDate(), dto.getCheckOutDate());

        Reservation reservation = ReservationMapper.toEntity(dto, room, user);

        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }

    // GET BY ID
    @Override
    public ReservationResponseDTO getReservationById(Long id) {

        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));

        return ReservationMapper.toDTO(r);
    }

    // GET ALL
    @Override
    public List<ReservationResponseDTO> getAllReservations() {

        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::toDTO)
                .toList();
    }

    // GET BY ROOM
    @Override
    public List<ReservationResponseDTO> getReservationsByRoom(Long roomId) {

        return reservationRepository.findByRoomId(roomId)
                .stream()
                .map(ReservationMapper::toDTO)
                .toList();
    }

    // GET BY EMAIL (CUSTOM FIX 🔥)
    @Override
    public List<ReservationResponseDTO> getReservationsByGuestEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        return reservationRepository.findByGuestEmailIgnoreCase(email)
                .stream()
                .map(ReservationMapper::toDTO)
                .toList();
    }
    // UPDATE
    @Override
    public ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO dto) {

        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        validateDates(dto.getCheckInDate(), dto.getCheckOutDate());

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
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));

        reservationRepository.delete(r);
    }

    // DATE VALIDATION (CLEAN 🔥)
    private void validateDates(LocalDate checkIn, LocalDate checkOut) {

        if (checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("Check-in must be before check-out");
        }

        if (checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in cannot be in the past");
        }
    }

	@Override
	public List<ReservationResponseDTO> getReservationsByUser(Long userId) {
		
		  if (userId == null) {
		        throw new IllegalArgumentException("User ID cannot be null");
		    }

		    List<Reservation> reservations = reservationRepository.findByUserId(userId);

		    if (reservations.isEmpty()) {
		        throw new ReservationNotFoundException("No reservations found for user id: " + userId);
		    }

		    return reservations.stream()
		            .map(ReservationMapper::toDTO)
		            .toList();
	}
}
